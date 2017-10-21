	public BasicCollectionMapper(
			CommonCollectionMapperData commonCollectionMapperData,
			Class<? extends T> collectionClass,
			Class<? extends T> proxyClass,
			MiddleComponentData elementComponentData,
			boolean ordinalInId,
			boolean revisionTypeInId) {
		super( commonCollectionMapperData, collectionClass, proxyClass, ordinalInId, revisionTypeInId );
		this.elementComponentData = elementComponentData;
	}
