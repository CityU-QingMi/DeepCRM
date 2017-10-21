		@Override
		public String[] sqlCreateStrings(Dialect dialect) {
			return new String[] {
					"CREATE ALIAS allEmployeeNames AS $$\n" +
							"import org.h2.tools.SimpleResultSet;\n" +
							"import java.sql.*;\n" +
							"@CODE\n" +
							"ResultSet allEmployeeNames() {\n" +
							"    SimpleResultSet rs = new SimpleResultSet();\n" +
							"    rs.addColumn(\"ID\", Types.INTEGER, 10, 0);\n" +
							"    rs.addColumn(\"FIRSTNAME\", Types.VARCHAR, 255, 0);\n" +
							"    rs.addColumn(\"LASTNAME\", Types.VARCHAR, 255, 0);\n" +
							"    rs.addRow(1, \"Steve\", \"Ebersole\");\n" +
							"    rs.addRow(1, \"Jane\", \"Doe\");\n" +
							"    rs.addRow(1, \"John\", \"Doe\");\n" +
							"    return rs;\n" +
							"}\n" +
							"$$"
			};
		}
