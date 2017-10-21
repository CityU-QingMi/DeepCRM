	@Override
	@SuppressWarnings("")
	public void start(BundleContext context) throws Exception {
		osgiServiceUtil = new OsgiServiceUtil( context );

		// Build a JtaPlatform specific for this OSGi context
		final OsgiJtaPlatform osgiJtaPlatform = new OsgiJtaPlatform( osgiServiceUtil );

		final Dictionary properties = new Hashtable();
		// In order to support existing persistence.xml files, register using the legacy provider name.
		properties.put( "javax.persistence.provider", HibernatePersistenceProvider.class.getName() );
		persistenceProviderService = context.registerService(
				PersistenceProvider.class.getName(),
				new OsgiPersistenceProviderService( osgiJtaPlatform, osgiServiceUtil ),
				properties
		);
		sessionFactoryService = context.registerService(
				SessionFactory.class.getName(),
				new OsgiSessionFactoryService( osgiJtaPlatform, osgiServiceUtil ),
				new Hashtable()
		);
	}
