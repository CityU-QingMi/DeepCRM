	public boolean isNaturalIdentifierInsertGenerated() {
		// the intention is for this call to replace the usage of the old ValueInclusion stuff (as exposed from
		// persister) in SelectGenerator to determine if it is safe to use the natural identifier to find the
		// insert-generated identifier.  That wont work if the natural-id is also insert-generated.
		//
		// Assumptions:
		//		* That code checks that there is a natural identifier before making this call, so we assume the same here
		// 		* That code assumes a non-composite natural-id, so we assume the same here
		final InDatabaseValueGenerationStrategy strategy = inDatabaseValueGenerationStrategies[ naturalIdPropertyNumbers[0] ];
		return strategy != null && strategy.getGenerationTiming() != GenerationTiming.NEVER;
	}
