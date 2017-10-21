	public static JdbcConnectionAccess buildBootstrapJdbcConnectionAccess(
			MultiTenancyStrategy multiTenancyStrategy,
			ServiceRegistryImplementor registry) {
		if ( !multiTenancyStrategy.requiresMultiTenantConnectionProvider() ) {
			ConnectionProvider connectionProvider = registry.getService( ConnectionProvider.class );
			return new ConnectionProviderJdbcConnectionAccess( connectionProvider );
		}
		else {
			final MultiTenantConnectionProvider multiTenantConnectionProvider = registry.getService( MultiTenantConnectionProvider.class );
			return new MultiTenantConnectionProviderJdbcConnectionAccess( multiTenantConnectionProvider );
		}
	}
