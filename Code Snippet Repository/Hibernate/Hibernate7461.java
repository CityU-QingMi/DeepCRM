	@Before
	public void clearCounts() {
		// in case we add additional tests
		sessionFactory().getStatistics().clear();

		mutableToDatabaseCallCount = 0;
		mutableToDomainCallCount = 0;

		immutableToDatabaseCallCount = 0;
		immutableToDomainCallCount = 0;

		immutableMutableToDatabaseCallCount = 0;
		immutableMutableToDomainCallCount = 0;
	}
