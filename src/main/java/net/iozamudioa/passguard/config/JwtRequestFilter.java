package net.iozamudioa.passguard.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import net.iozamudioa.passguard.dto.secure.AuthUser;
import net.iozamudioa.passguard.service.impl.CustomUserDetailsService;
import net.iozamudioa.passguard.util.Constant;

@Component
@Log4j2
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {

    final String requestTokenHeader = request.getHeader("Authorization");

    String username = null;
    String jwtToken = null;
    if (requestTokenHeader != null && requestTokenHeader.startsWith(Constant.PREFIX_TOKEN)) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        username = jwtTokenUtil.getUsernameFromToken(jwtToken);


      } catch (IllegalArgumentException e) {
        log.error("Unable to get JWT Token");
      } catch (ExpiredJwtException e) {
        log.error("JWT Token has expired");
      }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      AuthUser authUser = this.customUserDetailsService.loadUserByUsername(username);
      request.setAttribute("idUser", authUser.getIdUser());

      if (jwtTokenUtil.validateToken(jwtToken, authUser)) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken((UserDetails) authUser, null,
                authUser.getAuthorities());
        usernamePasswordAuthenticationToken
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    chain.doFilter(request, response);
  }

}
