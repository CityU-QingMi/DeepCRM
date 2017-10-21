	public SortedMapCollectionMapper(
			CommonCollectionMapperData commonCollectionMapperData,
			Class<? extends SortedMap> collectionClass, Class<? extends SortedMap> proxyClass,
			MiddleComponentData elementComponentData, MiddleComponentData indexComponentData, Comparator comparator,
			boolean revisionTypeInId) {
		super(
				commonCollectionMapperData,
				collectionClass,
				proxyClass,
				elementComponentData,
				indexComponentData,
				revisionTypeInId
		);
		this.comparator = comparator;
	}
