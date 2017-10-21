	private AbstractEntitySourceImpl locateExtendedEntitySource(
			MappingDocument mappingDocument,
			JaxbHbmSubclassEntityBaseDefinition jaxbSubEntityMapping) {
		// NOTE : extends may refer to either an entity-name or a class-name, we need to check each

		// first check using entity-name
		AbstractEntitySourceImpl entityItExtends = entitySourceByNameMap.get( jaxbSubEntityMapping.getExtends() );
		if ( entityItExtends == null ) {
			// next, check using class name
			entityItExtends = entitySourceByNameMap.get(
					mappingDocument.qualifyClassName( jaxbSubEntityMapping.getExtends() )
			);
		}

		return entityItExtends;
	}
