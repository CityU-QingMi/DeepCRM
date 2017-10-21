	private ValueGeneration determineValueGenerationStrategy(XProperty property) {
		ValueGeneration valueGeneration = getValueGenerationFromAnnotations( property );

		if ( valueGeneration == null ) {
			return NoValueGeneration.INSTANCE;
		}

		final GenerationTiming when = valueGeneration.getGenerationTiming();

		if ( valueGeneration.getValueGenerator() == null ) {
			insertable = false;
			if ( when == GenerationTiming.ALWAYS ) {
				updatable = false;
			}
		}

		return valueGeneration;
	}
