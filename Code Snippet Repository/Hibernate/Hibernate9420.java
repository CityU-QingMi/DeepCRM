	@Test
	public void testBuildWithServiceOverride() {
		StandardServiceRegistryImpl serviceRegistry = (StandardServiceRegistryImpl) new StandardServiceRegistryBuilder()
				.applySettings( ConnectionProviderBuilder.getConnectionProviderProperties() )
				.build();

		Properties props = ConnectionProviderBuilder.getConnectionProviderProperties();
		props.setProperty( Environment.DIALECT, H2Dialect.class.getName() );

		try {
			JdbcServices jdbcServices = serviceRegistry.getService( JdbcServices.class );

			assertTrue( jdbcServices.getDialect() instanceof H2Dialect );
			ConnectionProviderJdbcConnectionAccess connectionAccess = assertTyping(
					ConnectionProviderJdbcConnectionAccess.class,
					jdbcServices.getBootstrapJdbcConnectionAccess()
			);
			assertTrue( connectionAccess.getConnectionProvider().isUnwrappableAs( DriverManagerConnectionProviderImpl.class ) );
		}
		finally {
			serviceRegistry.destroy();
		}

		try {
			serviceRegistry = (StandardServiceRegistryImpl) new StandardServiceRegistryBuilder()
					.applySettings( props )
					.addService( ConnectionProvider.class, new UserSuppliedConnectionProviderImpl() )
					.build();
			JdbcServices jdbcServices = serviceRegistry.getService( JdbcServices.class );

			assertTrue( jdbcServices.getDialect() instanceof H2Dialect );
			ConnectionProviderJdbcConnectionAccess connectionAccess = assertTyping(
					ConnectionProviderJdbcConnectionAccess.class,
					jdbcServices.getBootstrapJdbcConnectionAccess()
			);
			assertTrue( connectionAccess.getConnectionProvider().isUnwrappableAs( UserSuppliedConnectionProviderImpl.class ) );
		}
		finally {
			serviceRegistry.destroy();
		}
	}
