	private static void interpretPartialCompositeValueGeneration(
			SessionFactoryImplementor sessionFactory,
			Component composite,
			CompositeGenerationStrategyPairBuilder builder) {
		Iterator subProperties = composite.getPropertyIterator();
		while ( subProperties.hasNext() ) {
			final Property subProperty = (Property) subProperties.next();
			builder.addPair( buildGenerationStrategyPair( sessionFactory, subProperty ) );
		}
	}
