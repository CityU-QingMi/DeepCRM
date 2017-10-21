	private EntityManagerFactory getEntityManagerFactory() {
		if ( emf == null ) {
			Bundle thisBundle = FrameworkUtil.getBundle( HibernateUtil.class );
			// Could get this by wiring up OsgiTestBundleActivator as well.
			BundleContext context = thisBundle.getBundleContext();

			ServiceReference serviceReference = context.getServiceReference( PersistenceProvider.class.getName() );
			PersistenceProvider persistenceProvider = (PersistenceProvider) context.getService( serviceReference );

			emf = persistenceProvider.createEntityManagerFactory( "unmanaged-jpa", null );
		}
		return emf;
	}
