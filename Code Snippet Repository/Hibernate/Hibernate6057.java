	@Override
	protected void afterEntityManagerFactoryBuilt() {
		super.afterEntityManagerFactoryBuilt();

		Session s = entityManagerFactory().unwrap( SessionFactory.class ).openSession();
		s.doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						connection.createStatement().execute(
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
						);
					}
				}
		);
		s.close();
	}
