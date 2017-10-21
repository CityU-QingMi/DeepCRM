	@Test
	public void testProperties() throws Exception {
		final Properties props = new Properties();

		props.put("rpt.1.hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
		props.put("rpt.2.hibernate.connection.driver_class", "org.apache.derby.jdbc.ClientDriver");
		props.put("rpt.3.hibernate.connection.url", "jdbc:derby://localhost:1527/db/reports.db");
		props.put("rpt.4.hibernate.connection.username", "sa");
		props.put("rpt.5.hibernate.connection.password_enc", "76f271db3661fd50082e68d4b953fbee");
		props.put("rpt.6.hibernate.connection.password_enc", "76f271db3661fd50082e68d4b953fbee");
		props.put("hibernate.connection.create", "true");

		final Properties outputProps = ConnectionProviderInitiator.getConnectionProperties( props );
		Assert.assertEquals( 1, outputProps.size() );
		Assert.assertEquals( "true", outputProps.get( "create" ) );
	}
