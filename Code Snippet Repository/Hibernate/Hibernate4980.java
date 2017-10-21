	private static GenerationStrategyPair buildGenerationStrategyPair(
			final SessionFactoryImplementor sessionFactory,
			final Property mappingProperty) {
		final ValueGeneration valueGeneration = mappingProperty.getValueGenerationStrategy();
		if ( valueGeneration != null && valueGeneration.getGenerationTiming() != GenerationTiming.NEVER ) {
			// the property is generated in full. build the generation strategy pair.
			if ( valueGeneration.getValueGenerator() != null ) {
				// in-memory generation
				return new GenerationStrategyPair(
						FullInMemoryValueGenerationStrategy.create( valueGeneration )
				);
			}
			else {
				// in-db generation
				return new GenerationStrategyPair(
						create(
								sessionFactory,
								mappingProperty,
								valueGeneration
						)
				);
			}
		}
		else if ( mappingProperty.getValue() instanceof Component ) {
			final CompositeGenerationStrategyPairBuilder builder = new CompositeGenerationStrategyPairBuilder( mappingProperty );
			interpretPartialCompositeValueGeneration( sessionFactory, (Component) mappingProperty.getValue(), builder );
			return builder.buildPair();
		}

		return NO_GEN_PAIR;
	}
