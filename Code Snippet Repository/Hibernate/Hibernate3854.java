	public CollectionType getCollectionType() {
		if ( typeName == null ) {
			return getDefaultCollectionType();
		}
		else {
			return metadata.getTypeResolver()
					.getTypeFactory()
					.customCollection( typeName, typeParameters, role, referencedPropertyName );
		}
	}
