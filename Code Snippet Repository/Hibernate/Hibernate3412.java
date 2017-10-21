	private static Map<String, AttributeNodeImplementor<?>> makeSafeMapCopy(Map<String, AttributeNodeImplementor<?>> attributeNodeMap) {
		if ( attributeNodeMap == null ) {
			return null;
		}

		final int properSize = CollectionHelper.determineProperSizing( attributeNodeMap );
		final HashMap<String,AttributeNodeImplementor<?>> copy = new HashMap<>( properSize );
		for ( Map.Entry<String,AttributeNodeImplementor<?>> attributeNodeEntry : attributeNodeMap.entrySet() ) {
			copy.put(
					attributeNodeEntry.getKey(),
					( ( AttributeNodeImpl ) attributeNodeEntry.getValue() ).makeImmutableCopy()
			);
		}
		return copy;
	}
