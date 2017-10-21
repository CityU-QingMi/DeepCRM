	protected AbstractDataSourceBasedMultiTenantConnectionProviderImpl buildMultiTenantConnectionProvider() {
		acmeProvider = ConnectionProviderBuilder.buildDataSourceConnectionProvider( "acme" );
		jbossProvider = ConnectionProviderBuilder.buildDataSourceConnectionProvider( "jboss" );
		return new AbstractDataSourceBasedMultiTenantConnectionProviderImpl() {
			@Override
			protected DataSource selectAnyDataSource() {
				return acmeProvider.unwrap( DataSource.class );
			}

			@Override
			protected DataSource selectDataSource(String tenantIdentifier) {
				if ( "acme".equals( tenantIdentifier ) ) {
					return acmeProvider.unwrap( DataSource.class );
				}
				else if ( "jboss".equals( tenantIdentifier ) ) {
					return jbossProvider.unwrap( DataSource.class );
				}
				throw new HibernateException( "Unknown tenant identifier" );
			}
		};
	}
