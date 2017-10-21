	public static Class reflectedPropertyClass(
			MetadataBuildingContext buildingContext,
			String attributeOwnerClassName,
			String attributeName) {
		final Class attributeOwnerClass = buildingContext.getClassLoaderAccess().classForName( attributeOwnerClassName );
		return reflectedPropertyClass(
				buildingContext,
				attributeOwnerClass,
				attributeName
		);
	}
