	private void processSubEntityElements(
			MappingDocument mappingDocument,
			JaxbHbmEntityBaseDefinition entityBinding,
			AbstractEntitySourceImpl container) {
		if ( JaxbHbmDiscriminatorSubclassEntityType.class.isInstance( entityBinding ) ) {
			final JaxbHbmDiscriminatorSubclassEntityType jaxbSubclass = (JaxbHbmDiscriminatorSubclassEntityType) entityBinding;
			processElements( mappingDocument, jaxbSubclass.getSubclass(), container );
		}
		else if ( JaxbHbmJoinedSubclassEntityType.class.isInstance( entityBinding ) ) {
			final JaxbHbmJoinedSubclassEntityType jaxbJoinedSubclass = (JaxbHbmJoinedSubclassEntityType) entityBinding;
			processElements( mappingDocument, jaxbJoinedSubclass.getJoinedSubclass(), container );
		}
		else if ( JaxbHbmUnionSubclassEntityType.class.isInstance( entityBinding ) ) {
			final JaxbHbmUnionSubclassEntityType jaxbUnionSubclass = (JaxbHbmUnionSubclassEntityType) entityBinding;
			processElements( mappingDocument, jaxbUnionSubclass.getUnionSubclass(), container );
		}
	}
