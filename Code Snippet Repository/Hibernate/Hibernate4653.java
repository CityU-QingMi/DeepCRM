	public SessionFactoryServiceRegistry buildSessionFactoryServiceRegistry(
			SessionFactoryImplementor sessionFactory,
			SessionFactoryOptions options) {
		return new SessionFactoryServiceRegistryImpl(
				parent,
				initiators,
				providedServices,
				sessionFactory,
				options
		);
	}
