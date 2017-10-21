	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		final Object dataSourceConfigValue = serviceRegistry.getService( ConfigurationService.class )
				.getSettings()
				.get( AvailableSettings.DATASOURCE );
		if ( dataSourceConfigValue == null || ! String.class.isInstance( dataSourceConfigValue ) ) {
			throw new HibernateException( "Improper set up of DataSourceBasedMultiTenantConnectionProviderImpl" );
		}
		final String jndiName = (String) dataSourceConfigValue;

		jndiService = serviceRegistry.getService( JndiService.class );
		if ( jndiService == null ) {
			throw new HibernateException( "Could not locate JndiService from DataSourceBasedMultiTenantConnectionProviderImpl" );
		}

		final Object namedObject = jndiService.locate( jndiName );
		if ( namedObject == null ) {
			throw new HibernateException( "JNDI name [" + jndiName + "] could not be resolved" );
		}

		if ( DataSource.class.isInstance( namedObject ) ) {
			final int loc = jndiName.lastIndexOf( "/" );
			this.baseJndiNamespace = jndiName.substring( 0, loc );
			this.tenantIdentifierForAny = jndiName.substring( loc + 1 );
			dataSourceMap().put( tenantIdentifierForAny, (DataSource) namedObject );
		}
		else if ( Context.class.isInstance( namedObject ) ) {
			this.baseJndiNamespace = jndiName;
			this.tenantIdentifierForAny = (String) serviceRegistry.getService( ConfigurationService.class )
					.getSettings()
					.get( TENANT_IDENTIFIER_TO_USE_FOR_ANY_KEY );
			if ( tenantIdentifierForAny == null ) {
				throw new HibernateException( "JNDI name named a Context, but tenant identifier to use for ANY was not specified" );
			}
		}
		else {
			throw new HibernateException(
					"Unknown object type [" + namedObject.getClass().getName() +
							"] found in JNDI location [" + jndiName + "]"
			);
		}
	}
