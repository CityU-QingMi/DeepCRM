	public IntegratorServiceImpl(LinkedHashSet<Integrator> providedIntegrators, ClassLoaderService classLoaderService) {
		// register standard integrators.  Envers and JPA, for example, need to be handled by discovery because in
		// separate project/jars.
		addIntegrator( new BeanValidationIntegrator() );
		addIntegrator( new JaccIntegrator() );
		addIntegrator( new CollectionCacheInvalidator() );

		// register provided integrators
		for ( Integrator integrator : providedIntegrators ) {
			addIntegrator( integrator );
		}

		for ( Integrator integrator : classLoaderService.loadJavaServices( Integrator.class ) ) {
			addIntegrator( integrator );
		}
	}
