package net.iozamudioa.passguard.controller;

import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class RootController {

  @GetMapping("/")
  public ModelAndView root(ModelMap model) {
    log.info("view {}", new Date());
    return new ModelAndView("redirect:/swagger-ui/", model);
  }


}
