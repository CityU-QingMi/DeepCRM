	@Test
	public void testBasicBuild() {
		// this test requires that SHOW_SQL property isn't passed from the outside (eg. via Gradle)
		final String showSqlPropertyFromOutside = System.getProperty(Environment.SHOW_SQL);
		Assume.assumeFalse("true".equals(showSqlPropertyFromOutside));

		final StandardServiceRegistryImpl serviceRegistry = (StandardServiceRegistryImpl) new StandardServiceRegistryBuilder()
				.applySettings( ConnectionProviderBuilder.getConnectionProviderProperties() )
				.build();
		try {
			final JdbcServices jdbcServices = serviceRegistry.getService( JdbcServices.class );
			assertTrue( jdbcServices.getDialect() instanceof H2Dialect );
			final ConnectionProviderJdbcConnectionAccess connectionAccess = assertTyping(
					ConnectionProviderJdbcConnectionAccess.class,
					jdbcServices.getBootstrapJdbcConnectionAccess()
			);
			assertTrue( connectionAccess.getConnectionProvider().isUnwrappableAs( DriverManagerConnectionProviderImpl.class ) );
			assertFalse( jdbcServices.getSqlStatementLogger().isLogToStdout() );
		}
		finally {
			serviceRegistry.destroy();
		}
	}
