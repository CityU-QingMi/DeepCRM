	private void processRootEntitySubEntityElements(
			MappingDocument mappingDocument,
			JaxbHbmRootEntityType jaxbRootEntity,
			RootEntitySourceImpl rootEntitySource) {
		// todo : technically we should only allow one mutually-exclusive; should we enforce that here?
		//		I believe the DTD/XSD does enforce that, so maybe not a big deal
		processElements( mappingDocument, jaxbRootEntity.getSubclass(), rootEntitySource );
		processElements( mappingDocument, jaxbRootEntity.getJoinedSubclass(), rootEntitySource );
		processElements( mappingDocument, jaxbRootEntity.getUnionSubclass(), rootEntitySource );
	}
