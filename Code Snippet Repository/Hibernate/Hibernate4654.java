	@Override
	public SessionFactoryServiceRegistry buildServiceRegistry(
			SessionFactoryImplementor sessionFactory,
			SessionFactoryOptions options) {
		final ClassLoaderService cls = options.getServiceRegistry().getService( ClassLoaderService.class );
		final SessionFactoryServiceRegistryBuilderImpl builder = new SessionFactoryServiceRegistryBuilderImpl( theBasicServiceRegistry );

		for ( SessionFactoryServiceContributor contributor : cls.loadJavaServices( SessionFactoryServiceContributor.class ) ) {
			contributor.contribute( builder );
		}

		return builder.buildSessionFactoryServiceRegistry( sessionFactory, options );
	}
