	private JdbcConnectionAccess buildJdbcConnectionAccess(Map configValues, ServiceRegistryImplementor registry) {
		final MultiTenancyStrategy multiTenancyStrategy = MultiTenancyStrategy.determineMultiTenancyStrategy(
				configValues
		);
		if ( !multiTenancyStrategy.requiresMultiTenantConnectionProvider() ) {
			ConnectionProvider connectionProvider = registry.getService( ConnectionProvider.class );
			return new ConnectionProviderJdbcConnectionAccess( connectionProvider );
		}
		else {
			final MultiTenantConnectionProvider multiTenantConnectionProvider = registry.getService( MultiTenantConnectionProvider.class );
			return new MultiTenantConnectionProviderJdbcConnectionAccess( multiTenantConnectionProvider );
		}
	}
