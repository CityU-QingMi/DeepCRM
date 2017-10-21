	@Override
	@SuppressWarnings("")
	public EntityManagerFactory createContainerEntityManagerFactory(PersistenceUnitInfo info, Map properties) {
		final Map settings = generateSettings( properties );

		// OSGi ClassLoaders must implement BundleReference
		settings.put(
				org.hibernate.cfg.AvailableSettings.SCANNER,
				new OsgiScanner( ( (BundleReference) info.getClassLoader() ).getBundle() )
		);

		osgiClassLoader.addClassLoader( info.getClassLoader() );
		
		return Bootstrap.getEntityManagerFactoryBuilder( info, settings,
				new OSGiClassLoaderServiceImpl( osgiClassLoader, osgiServiceUtil ) ).build();
	}
