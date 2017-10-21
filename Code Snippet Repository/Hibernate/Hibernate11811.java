	@Override
	@SuppressWarnings("")
	public EntityManagerFactory createEntityManagerFactory(String persistenceUnitName, Map properties) {
		final Map settings = generateSettings( properties );

		// TODO: This needs tested.
		settings.put( org.hibernate.cfg.AvailableSettings.SCANNER, new OsgiScanner( requestingBundle ) );
		// TODO: This is temporary -- for PersistenceXmlParser's use of
		// ClassLoaderServiceImpl#fromConfigSettings
		settings.put( AvailableSettings.ENVIRONMENT_CLASSLOADER, osgiClassLoader );

		osgiClassLoader.addBundle( requestingBundle );

		final EntityManagerFactoryBuilder builder = getEntityManagerFactoryBuilderOrNull( persistenceUnitName, settings,
				new OSGiClassLoaderServiceImpl( osgiClassLoader, osgiServiceUtil ) );
		return builder == null ? null : builder.build();
	}
