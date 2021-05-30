package net.iozamudioa.passguard.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class UtilDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public Map<String, Object> executeProcedure(String procedureName, Object... params) {
    SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
    simpleJdbcCall.setProcedureName(procedureName);
    return params != null ? simpleJdbcCall.execute(params) : simpleJdbcCall.execute();
  }

  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> procedureForResultSet(String procedureName, Object... params) {
    Map<String, Object> out = executeProcedure(procedureName, params);
    return (List<Map<String, Object>>) out.get("#result-set-1");
  }

  public List<Map<String, Object>> procedureForList(String procedureName, Object... params) {
    List<Map<String, Object>> result = this.procedureForResultSet(procedureName, params);
    return result.isEmpty() ? new ArrayList<>() : result;
  }

  public Map<String, Object> procedureForMap(String procedureName, Object... params) {
    List<Map<String, Object>> result = this.procedureForResultSet(procedureName, params);
    return result.isEmpty() ? new HashMap<>() : result.get(0);
  }

  public <T> T callFunction(String nombreFuncion, Class<T> clazz, Object... args) {
    SimpleJdbcCall jdbcCall =
        new SimpleJdbcCall(jdbcTemplate.getDataSource()).withFunctionName(nombreFuncion);
    return jdbcCall.executeFunction(clazz, args);
  }

}
