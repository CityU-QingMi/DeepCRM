	@Override
	@SuppressWarnings( {""})
	public MultiTenantConnectionProvider initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		final MultiTenancyStrategy strategy = MultiTenancyStrategy.determineMultiTenancyStrategy(  configurationValues );
		if ( !strategy.requiresMultiTenantConnectionProvider() ) {
			// nothing to do, but given the separate hierarchies have to handle this here.
			return null;
		}

		final Object configValue = configurationValues.get( AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER );
		if ( configValue == null ) {
			// if they also specified the data source *name*, then lets assume they want
			// DataSourceBasedMultiTenantConnectionProviderImpl
			final Object dataSourceConfigValue = configurationValues.get( AvailableSettings.DATASOURCE );
			if ( dataSourceConfigValue != null && String.class.isInstance( dataSourceConfigValue ) ) {
				return new DataSourceBasedMultiTenantConnectionProviderImpl();
			}

			return null;
		}

		if ( MultiTenantConnectionProvider.class.isInstance( configValue ) ) {
			return (MultiTenantConnectionProvider) configValue;
		}
		else {
			final Class<MultiTenantConnectionProvider> implClass;
			if ( Class.class.isInstance( configValue ) ) {
				implClass = (Class) configValue;
			}
			else {
				final String className = configValue.toString();
				final ClassLoaderService classLoaderService = registry.getService( ClassLoaderService.class );
				try {
					implClass = classLoaderService.classForName( className );
				}
				catch (ClassLoadingException cle) {
					log.warn( "Unable to locate specified class [" + className + "]", cle );
					throw new ServiceException( "Unable to locate specified multi-tenant connection provider [" + className + "]" );
				}
			}

			try {
				return implClass.newInstance();
			}
			catch (Exception e) {
				log.warn( "Unable to instantiate specified class [" + implClass.getName() + "]", e );
				throw new ServiceException( "Unable to instantiate specified multi-tenant connection provider [" + implClass.getName() + "]" );
			}
		}
	}
