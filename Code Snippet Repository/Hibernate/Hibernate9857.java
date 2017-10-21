	private boolean isPropertyInsertable(Property property) {
		if ( !property.isInsertable() ) {
			final ValueGeneration generation = property.getValueGenerationStrategy();
			if ( generation instanceof GeneratedValueGeneration ) {
				final GeneratedValueGeneration valueGeneration = (GeneratedValueGeneration) generation;
				if ( GenerationTiming.INSERT == valueGeneration.getGenerationTiming()
					|| GenerationTiming.ALWAYS == valueGeneration.getGenerationTiming() ) {
					return true;
				}
			}
		}
		return property.isInsertable();
	}
