	public static CollectionPropertyHolder buildPropertyHolder(
			Collection collection,
			String path,
			XClass clazzToProcess,
			XProperty property,
			PropertyHolder parentPropertyHolder,
			MetadataBuildingContext context) {
		return new CollectionPropertyHolder(
				collection,
				path,
				clazzToProcess,
				property,
				parentPropertyHolder,
				context
		);
	}
