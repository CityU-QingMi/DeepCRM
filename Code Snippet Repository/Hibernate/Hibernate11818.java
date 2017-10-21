	private Object buildSessionFactory(
			Bundle requestingBundle,
			OsgiClassLoader osgiClassLoader) {
		final BootstrapServiceRegistryBuilder bsrBuilder = new BootstrapServiceRegistryBuilder();
		bsrBuilder.applyClassLoaderService( new OSGiClassLoaderServiceImpl( osgiClassLoader, osgiServiceUtil ) );

		final Integrator[] integrators = osgiServiceUtil.getServiceImpls( Integrator.class );
		for ( Integrator integrator : integrators ) {
			bsrBuilder.applyIntegrator( integrator );
		}

		final StrategyRegistrationProvider[] strategyRegistrationProviders
				= osgiServiceUtil.getServiceImpls( StrategyRegistrationProvider.class );
		for ( StrategyRegistrationProvider strategyRegistrationProvider : strategyRegistrationProviders ) {
			bsrBuilder.applyStrategySelectors( strategyRegistrationProvider );
		}

		final BootstrapServiceRegistry bsr = bsrBuilder.build();
		final StandardServiceRegistryBuilder ssrBuilder = new StandardServiceRegistryBuilder( bsr );

		// Allow bundles to put the config file somewhere other than the root level.
		final BundleWiring bundleWiring = (BundleWiring) requestingBundle.adapt( BundleWiring.class );
		final Collection<String> cfgResources = bundleWiring.listResources(
				"/",
				"hibernate.cfg.xml",
				BundleWiring.LISTRESOURCES_RECURSE
		);
		if (cfgResources.size() == 0) {
			ssrBuilder.configure();
		}
		else {
			if (cfgResources.size() > 1) {
				LOG.warn( "Multiple hibernate.cfg.xml files found in the persistence bundle.  Using the first one discovered." );
			}
			String cfgResource = "/" + cfgResources.iterator().next();
			ssrBuilder.configure( cfgResource );
		}

		ssrBuilder.applySetting( AvailableSettings.JTA_PLATFORM, osgiJtaPlatform );

		final StandardServiceRegistry ssr = ssrBuilder.build();

		final MetadataBuilder metadataBuilder = new MetadataSources( ssr ).getMetadataBuilder();
		final TypeContributor[] typeContributors = osgiServiceUtil.getServiceImpls( TypeContributor.class );
		for ( TypeContributor typeContributor : typeContributors ) {
			metadataBuilder.applyTypes( typeContributor );
		}

		return metadataBuilder.build().buildSessionFactory();
	}
