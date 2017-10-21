	@Override
	@SuppressWarnings("")
	public Iterable<StrategyRegistration> getStrategyRegistrations() {
		final List<StrategyRegistration> strategyRegistrations = new ArrayList<StrategyRegistration>();

		strategyRegistrations.add(
				new SimpleStrategyRegistrationImpl(
						RegionFactory.class,
						EhCacheRegionFactory.class,
						"ehcache",
						EhCacheRegionFactory.class.getName(),
						EhCacheRegionFactory.class.getSimpleName(),
						// legacy impl class name
						"org.hibernate.cache.EhCacheRegionFactory"
				)
		);

		strategyRegistrations.add(
				new SimpleStrategyRegistrationImpl(
						RegionFactory.class,
						SingletonEhCacheRegionFactory.class,
						"ehcache-singleton",
						SingletonEhCacheRegionFactory.class.getName(),
						SingletonEhCacheRegionFactory.class.getSimpleName(),
						// legacy impl class name
						"org.hibernate.cache.SingletonEhCacheRegionFactory"
				)
		);

		return strategyRegistrations;
	}
