		private void add(InDatabaseValueGenerationStrategy inDatabaseStrategy) {
			if ( inDatabaseStrategies == null ) {
				inDatabaseStrategies = new ArrayList<InDatabaseValueGenerationStrategy>();
			}
			inDatabaseStrategies.add( inDatabaseStrategy );

			if ( inDatabaseStrategy.getGenerationTiming() != GenerationTiming.NEVER ) {
				hadInDatabaseGeneration = true;
			}
		}
