	@Override
	public Object getService(Bundle requestingBundle, ServiceRegistration registration) {
		final OsgiClassLoader osgiClassLoader = new OsgiClassLoader();

		// First, add the client bundle that's requesting the OSGi services.
		osgiClassLoader.addBundle( requestingBundle );

		// Then, automatically add hibernate-core and hibernate-entitymanager.  These are needed to load resources
		// contained in those jars, such as em's persistence.xml schemas.
		osgiClassLoader.addBundle( FrameworkUtil.getBundle( SessionFactory.class ) );
		osgiClassLoader.addBundle( FrameworkUtil.getBundle( HibernateEntityManagerFactory.class ) );

		// Some "boot time" code does still rely on TCCL.  "run time" code should all be using
		// ClassLoaderService now.

		final ClassLoader originalTccl = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader( osgiClassLoader );
		try {
			return new OsgiPersistenceProvider( osgiClassLoader, osgiJtaPlatform, osgiServiceUtil, requestingBundle );
		}
		finally {
			Thread.currentThread().setContextClassLoader( originalTccl );
		}
	}
