	@Override
	public Iterable<StrategyRegistration> getStrategyRegistrations() {
		final List<StrategyRegistration> strategyRegistrations = new ArrayList<StrategyRegistration>();

		strategyRegistrations.add(
				new SimpleStrategyRegistrationImpl<RegionFactory>(
						RegionFactory.class,
						InfinispanRegionFactory.class,
						"infinispan",
						InfinispanRegionFactory.class.getName(),
						InfinispanRegionFactory.class.getSimpleName()
				)
		);

		strategyRegistrations.add(
				new SimpleStrategyRegistrationImpl<RegionFactory>(
						RegionFactory.class,
						JndiInfinispanRegionFactory.class,
						"infinispan-jndi",
						JndiInfinispanRegionFactory.class.getName(),
						JndiInfinispanRegionFactory.class.getSimpleName()
				)
		);

		return strategyRegistrations;
	}
