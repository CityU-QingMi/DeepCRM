	private static ValidatorFactory getValidatorFactory(ActivationContext activationContext) {
		// IMPL NOTE : We can either be provided a ValidatorFactory or make one.  We can be provided
		// a ValidatorFactory in 2 different ways.  So here we "get" a ValidatorFactory in the following order:
		//		1) Look into SessionFactoryOptions.getValidatorFactoryReference()
		//		2) Look into ConfigurationService
		//		3) build a new ValidatorFactory

		// 1 - look in SessionFactoryOptions.getValidatorFactoryReference()
		ValidatorFactory factory = resolveProvidedFactory( activationContext.getSessionFactory().getSessionFactoryOptions() );
		if ( factory != null ) {
			return factory;
		}

		// 2 - look in ConfigurationService
		factory = resolveProvidedFactory( activationContext.getServiceRegistry().getService( ConfigurationService.class ) );
		if ( factory != null ) {
			return factory;
		}

		// 3 - build our own
		try {
			return Validation.buildDefaultValidatorFactory();
		}
		catch ( Exception e ) {
			throw new IntegrationException( "Unable to build the default ValidatorFactory", e );
		}
	}
