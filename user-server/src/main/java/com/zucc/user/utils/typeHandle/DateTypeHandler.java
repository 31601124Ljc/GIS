package com.zucc.user.utils.typeHandle;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class DateTypeHandler extends BaseTypeHandler<Date> {

  private static final String DATE_FORMAT1 = "yyyyMMddHHmmss";
  
  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType)
      throws SQLException {
    // TODO Auto-generated method stub
    ps.setString(i, DateFormatUtils.format(parameter, DATE_FORMAT1));
  }

  @Override
  public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
    // TODO Auto-generated method stub
    return doForNullable(rs.getString(columnName));
  }

  @Override
  public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    // TODO Auto-generated method stub
    return doForNullable(rs.getString(columnIndex));
  }

  @Override
  public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    // TODO Auto-generated method stub
    return doForNullable(cs.getString(columnIndex));
  }

  private Date doForNullable(String date) {
    if (StringUtils.isBlank(date)) {
      return null;
    }
    return com.zucc.user.utils.DateUtils.string2Date(date);
  }
}
