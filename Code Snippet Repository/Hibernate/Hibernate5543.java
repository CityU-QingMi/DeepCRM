	@SuppressWarnings("")
	private MetadataImplementor buildMetadata(SharedCacheMode mode) {
		Map settings = new HashMap();
		settings.put( AvailableSettings.SHARED_CACHE_MODE, mode );
		settings.put( Environment.CACHE_REGION_FACTORY, CustomRegionFactory.class.getName() );
		settings.put(
				AvailableSettings.LOADED_CLASSES,
				Arrays.asList(
						ExplicitlyCacheableEntity.class,
						ExplicitlyNonCacheableEntity.class,
						NoCacheableAnnotationEntity.class
				)
		);

		PersistenceUnitInfoAdapter adapter = new PersistenceUnitInfoAdapter();

		final EntityManagerFactoryBuilderImpl emfb = (EntityManagerFactoryBuilderImpl) Bootstrap.getEntityManagerFactoryBuilder(
				adapter,
				settings
		);

		emf = emfb.build();
		return emfb.getMetadata();
	}
