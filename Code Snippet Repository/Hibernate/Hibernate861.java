	private void processTopLevelSubClassBinding(
			MappingDocument mappingDocument,
			JaxbHbmSubclassEntityBaseDefinition jaxbSubEntityMapping) {
		final AbstractEntitySourceImpl entityItExtends = locateExtendedEntitySource( mappingDocument, jaxbSubEntityMapping );

		if ( entityItExtends == null ) {
			// we have not seen its declared super-type yet, add it to the queue to be linked up
			// later when (if) we do
			addToToBeLinkedQueue( mappingDocument, jaxbSubEntityMapping );
		}
		else {
			// we have seen its super-type already
			final SubclassEntitySourceImpl subEntitySource = createSubClassEntitySource(
					mappingDocument,
					jaxbSubEntityMapping,
					entityItExtends
			);
			entitySourceByNameMap.put( subEntitySource.getEntityNamingSource().getEntityName(), subEntitySource );
			entityItExtends.add( subEntitySource );

			// this may have been a "middle type".  So link any sub entities that may be waiting on it
			linkAnyWaiting( mappingDocument, subEntitySource );

			processSubEntityElements( mappingDocument, jaxbSubEntityMapping, subEntitySource );
		}
	}
