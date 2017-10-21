	private static Map<Class, Subgraph> makeSafeMapCopy(Map<Class, Subgraph> subgraphMap) {
		if ( subgraphMap == null ) {
			return null;
		}

		final int properSize = CollectionHelper.determineProperSizing( subgraphMap );
		final HashMap<Class,Subgraph> copy = new HashMap<>( properSize );
		for ( Map.Entry<Class, Subgraph> subgraphEntry : subgraphMap.entrySet() ) {
			copy.put(
					subgraphEntry.getKey(),
					( ( SubgraphImpl ) subgraphEntry.getValue() ).makeImmutableCopy()
			);
		}
		return copy;
	}
