	@Override
	@SuppressWarnings( {""})
	public PersisterClassResolver initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		final Object customImpl = configurationValues.get( IMPL_NAME );
		if ( customImpl == null ) {
			return new StandardPersisterClassResolver();
		}

		if ( PersisterClassResolver.class.isInstance( customImpl ) ) {
			return (PersisterClassResolver) customImpl;
		}

		final Class<? extends PersisterClassResolver> customImplClass = Class.class.isInstance( customImpl )
				? (Class<? extends PersisterClassResolver>) customImpl
				: locate( registry, customImpl.toString() );

		try {
			return customImplClass.newInstance();
		}
		catch (Exception e) {
			throw new ServiceException( "Could not initialize custom PersisterClassResolver impl [" + customImplClass.getName() + "]", e );
		}
	}
