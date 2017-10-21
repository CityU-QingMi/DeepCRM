	public SortedSetCollectionMapper(
			CommonCollectionMapperData commonCollectionMapperData,
			Class<? extends SortedSet> collectionClass, Class<? extends SortedSet> proxyClass,
			MiddleComponentData elementComponentData, Comparator comparator, boolean ordinalInId,
			boolean revisionTypeInId) {
		super(
				commonCollectionMapperData,
				collectionClass,
				proxyClass,
				elementComponentData,
				ordinalInId,
				revisionTypeInId
		);
		this.comparator = comparator;
	}
