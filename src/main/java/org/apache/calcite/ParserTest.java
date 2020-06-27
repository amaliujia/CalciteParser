package org.apache.calcite;

import org.apache.calcite.avatica.util.Casing;
import org.apache.calcite.avatica.util.Quoting;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.dialect.CalciteSqlDialect;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.parser.SqlParserImplFactory;
import org.apache.calcite.sql.parser.babel.SqlBabelParserImpl;
import org.apache.calcite.sql.util.SqlString;

public class ParserTest {
  public static void main(String[] args) {
    String query1 = "SELECT * FROM (SELECT a FROM abc)";

    // sets configuration of the parser
    SqlParser.Config config = getParserConfig(SqlBabelParserImpl.FACTORY);

    SqlParser myParser = SqlParser.create(query1, config);
    try {
      SqlNode sqlNode = myParser.parseQuery();
      SqlString sqlString = sqlNode.toSqlString(CalciteSqlDialect.DEFAULT);
      System.out.println(sqlString);
    } catch (SqlParseException e) {
      e.printStackTrace();
    }

  }

  public static SqlParser.Config getParserConfig(SqlParserImplFactory factory) {
    return SqlParser.configBuilder()
        .setParserFactory(factory)
        .setQuotedCasing(Casing.UNCHANGED)
        .setUnquotedCasing(Casing.UNCHANGED)
        .setQuoting(Quoting.DOUBLE_QUOTE)
        .build();
  }
}
