		public GenerationStrategyPair(
				InMemoryValueGenerationStrategy inMemoryStrategy,
				InDatabaseValueGenerationStrategy inDatabaseStrategy) {
			// perform some normalization.  Also check that only one (if any) strategy is specified
			if ( inMemoryStrategy == null ) {
				inMemoryStrategy = NoInMemoryValueGenerationStrategy.INSTANCE;
			}
			if ( inDatabaseStrategy == null ) {
				inDatabaseStrategy = NoInDatabaseValueGenerationStrategy.INSTANCE;
			}

			if ( inMemoryStrategy.getGenerationTiming() != GenerationTiming.NEVER
					&& inDatabaseStrategy.getGenerationTiming() != GenerationTiming.NEVER ) {
				throw new ValueGenerationStrategyException(
						"in-memory and in-database value generation are mutually exclusive"
				);
			}

			this.inMemoryStrategy = inMemoryStrategy;
			this.inDatabaseStrategy = inDatabaseStrategy;
		}
