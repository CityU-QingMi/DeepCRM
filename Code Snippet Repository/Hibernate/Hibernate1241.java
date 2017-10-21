	static PropertyData getPropertyOverriddenByMapperOrMapsId(
			boolean isId,
			PropertyHolder propertyHolder,
			String propertyName,
			MetadataBuildingContext buildingContext) {
		final XClass persistentXClass;
		try {
			persistentXClass = buildingContext.getBuildingOptions().getReflectionManager()
					.classForName( propertyHolder.getPersistentClass().getClassName() );
		}
		catch ( ClassLoadingException e ) {
			throw new AssertionFailure( "PersistentClass name cannot be converted into a Class", e);
		}
		if ( propertyHolder.isInIdClass() ) {
			PropertyData pd = buildingContext.getMetadataCollector().getPropertyAnnotatedWithIdAndToOne(
					persistentXClass,
					propertyName
			);
			if ( pd == null && buildingContext.getBuildingOptions().isSpecjProprietarySyntaxEnabled() ) {
				pd = buildingContext.getMetadataCollector().getPropertyAnnotatedWithMapsId(
						persistentXClass,
						propertyName
				);
			}
			return pd;
		}
		String propertyPath = isId ? "" : propertyName;
		return buildingContext.getMetadataCollector().getPropertyAnnotatedWithMapsId( persistentXClass, propertyPath );
	}
