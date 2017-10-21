	public static InfinispanRegionFactory startRegionFactory(ServiceRegistry serviceRegistry) {
		try {
			final ConfigurationService cfgService = serviceRegistry.getService( ConfigurationService.class );
			final Properties properties = toProperties( cfgService.getSettings() );

			String factoryType = cfgService.getSetting( AvailableSettings.CACHE_REGION_FACTORY, StandardConverters.STRING );
			Class clazz = Thread.currentThread().getContextClassLoader().loadClass( factoryType );
			InfinispanRegionFactory regionFactory;
			if (clazz == InfinispanRegionFactory.class) {
				regionFactory = new TestInfinispanRegionFactory(properties);
			}
			else {
				if (InfinispanRegionFactory.class.isAssignableFrom(clazz)) {
					regionFactory = createRegionFactory(clazz, properties);
				} else {
					throw new IllegalArgumentException(clazz + " is not InfinispanRegionFactory");
				}
			}

			final SessionFactoryOptionsImpl sessionFactoryOptions = new SessionFactoryOptionsImpl(
					  new SessionFactoryBuilderImpl.SessionFactoryOptionsStateStandardImpl(
								 (StandardServiceRegistry) serviceRegistry
					  )
			);

			regionFactory.start( sessionFactoryOptions, properties );

			return regionFactory;
		}
		catch (RuntimeException e) {
			throw e;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
