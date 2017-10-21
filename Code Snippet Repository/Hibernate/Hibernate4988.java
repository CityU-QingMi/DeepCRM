		private void add(InMemoryValueGenerationStrategy inMemoryStrategy) {
			if ( inMemoryStrategies == null ) {
				inMemoryStrategies = new ArrayList<InMemoryValueGenerationStrategy>();
			}
			inMemoryStrategies.add( inMemoryStrategy );

			if ( inMemoryStrategy.getGenerationTiming() != GenerationTiming.NEVER ) {
				hadInMemoryGeneration = true;
			}
		}
