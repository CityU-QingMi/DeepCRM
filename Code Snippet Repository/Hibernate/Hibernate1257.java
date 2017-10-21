	public CollectionPropertyHolder(
			Collection collection,
			String path,
			XClass clazzToProcess,
			XProperty property,
			PropertyHolder parentPropertyHolder,
			MetadataBuildingContext context) {
		super( path, parentPropertyHolder, clazzToProcess, context );
		this.collection = collection;
		setCurrentProperty( property );

		this.elementAttributeConversionInfoMap = new HashMap<String, AttributeConversionInfo>();
		this.keyAttributeConversionInfoMap = new HashMap<String, AttributeConversionInfo>();
	}
