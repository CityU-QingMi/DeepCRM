	private SubclassEntitySourceImpl createSubClassEntitySource(
			MappingDocument mappingDocument,
			JaxbHbmSubclassEntityBaseDefinition jaxbSubEntity,
			EntitySource superEntity) {
		if ( JaxbHbmJoinedSubclassEntityType.class.isInstance( jaxbSubEntity ) ) {
			return new JoinedSubclassEntitySourceImpl(
					mappingDocument,
					JaxbHbmJoinedSubclassEntityType.class.cast( jaxbSubEntity ),
					superEntity
			);
		}
		else {
			return new SubclassEntitySourceImpl( mappingDocument, jaxbSubEntity, superEntity );
		}
	}
