	@Override
	@SuppressWarnings( {""})
	public PersisterFactory initiateService(Map configurationValues, ServiceRegistryImplementor registry) {
		final Object customImpl = configurationValues.get( IMPL_NAME );
		if ( customImpl == null ) {
			return new PersisterFactoryImpl();
		}

		if ( PersisterFactory.class.isInstance( customImpl ) ) {
			return (PersisterFactory) customImpl;
		}

		final Class<? extends PersisterFactory> customImplClass = Class.class.isInstance( customImpl )
				? ( Class<? extends PersisterFactory> ) customImpl
				: locate( registry, customImpl.toString() );
		try {
			return customImplClass.newInstance();
		}
		catch (Exception e) {
			throw new ServiceException( "Could not initialize custom PersisterFactory impl [" + customImplClass.getName() + "]", e );
		}
	}
