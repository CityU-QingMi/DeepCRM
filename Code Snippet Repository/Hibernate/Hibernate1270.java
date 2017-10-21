	public ComponentPropertyHolder(
			Component component,
			String path,
			PropertyData inferredData,
			PropertyHolder parent,
			MetadataBuildingContext context) {
		super( path, parent, inferredData.getPropertyClass(), context );
		final XProperty embeddedXProperty = inferredData.getProperty();
		setCurrentProperty( embeddedXProperty );
		this.component = component;
		this.isOrWithinEmbeddedId =
				parent.isOrWithinEmbeddedId()
						|| ( embeddedXProperty != null &&
						( embeddedXProperty.isAnnotationPresent( Id.class )
								|| embeddedXProperty.isAnnotationPresent( EmbeddedId.class ) ) );
		this.isWithinElementCollection = parent.isWithinElementCollection() ||
			parent instanceof CollectionPropertyHolder;

		if ( embeddedXProperty != null ) {
//			this.virtual = false;
			this.embeddedAttributeName = embeddedXProperty.getName();
			this.attributeConversionInfoMap = processAttributeConversions( embeddedXProperty );
		}
		else {
			// could be either:
			// 		1) virtual/dynamic component
			// 		2) collection element/key

			// temp
//			this.virtual = true;
			this.embeddedAttributeName = "";
			this.attributeConversionInfoMap = processAttributeConversions( inferredData.getClassOrElement() );
		}
	}
