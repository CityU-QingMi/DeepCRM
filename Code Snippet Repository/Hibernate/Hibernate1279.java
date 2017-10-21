	private static BootstrapServiceRegistry getBootstrapRegistry(ServiceRegistry serviceRegistry) {
		if ( BootstrapServiceRegistry.class.isInstance( serviceRegistry ) ) {
			return (BootstrapServiceRegistry) serviceRegistry;
		}
		else if ( StandardServiceRegistry.class.isInstance( serviceRegistry ) ) {
			final StandardServiceRegistry ssr = (StandardServiceRegistry) serviceRegistry;
			return (BootstrapServiceRegistry) ssr.getParentServiceRegistry();
		}

		throw new HibernateException(
				"No ServiceRegistry was passed to Configuration#buildSessionFactory " +
						"and could not determine how to locate BootstrapServiceRegistry " +
						"from Configuration instantiation"
		);
	}
