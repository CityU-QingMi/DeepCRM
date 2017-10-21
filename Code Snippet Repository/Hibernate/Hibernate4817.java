	protected Map<Identifier, IndexInformation> indexes() {
		if ( indexes == null ) {
			final Map<Identifier, IndexInformation> indexMap = new HashMap<>();
			final Iterable<IndexInformation> indexes = extractor.getIndexes( this );
			for ( IndexInformation index : indexes ) {
				indexMap.put( index.getIndexIdentifier(), index );
			}
			this.indexes = indexMap;
		}
		return indexes;
	}
