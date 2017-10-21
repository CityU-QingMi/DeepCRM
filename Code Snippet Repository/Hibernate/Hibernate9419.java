	@Test
	public void testBuildWithLogging() {
		Properties props = ConnectionProviderBuilder.getConnectionProviderProperties();
		props.put( Environment.SHOW_SQL, "true" );

		StandardServiceRegistryImpl serviceRegistry = (StandardServiceRegistryImpl) new StandardServiceRegistryBuilder()
			.applySettings( props )
			.build();

		try {
			JdbcServices jdbcServices = serviceRegistry.getService( JdbcServices.class );

			assertTrue( jdbcServices.getDialect() instanceof H2Dialect );
			final ConnectionProviderJdbcConnectionAccess connectionAccess = assertTyping(
					ConnectionProviderJdbcConnectionAccess.class,
					jdbcServices.getBootstrapJdbcConnectionAccess()
			);
			assertTrue( connectionAccess.getConnectionProvider().isUnwrappableAs( DriverManagerConnectionProviderImpl.class ) );
			assertTrue( jdbcServices.getSqlStatementLogger().isLogToStdout() );
		}
		finally {
			serviceRegistry.destroy();
		}
	}
