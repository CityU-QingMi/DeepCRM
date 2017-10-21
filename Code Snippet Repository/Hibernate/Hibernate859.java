	private void processElements(
			MappingDocument mappingDocument,
			List<? extends JaxbHbmSubclassEntityBaseDefinition> nestedSubEntityList,
			AbstractEntitySourceImpl container) {
		for ( final JaxbHbmSubclassEntityBaseDefinition jaxbSubEntity : nestedSubEntityList ) {
			final SubclassEntitySourceImpl subClassEntitySource = createSubClassEntitySource(
					mappingDocument,
					jaxbSubEntity,
					container
			);
			entitySourceByNameMap.put(
					subClassEntitySource.getEntityNamingSource().getEntityName(),
					subClassEntitySource
			);
			container.add( subClassEntitySource );
			linkAnyWaiting( mappingDocument, subClassEntitySource );

			// Re-run the sub element to handle subclasses within the subclass.
			processSubEntityElements( mappingDocument, jaxbSubEntity, subClassEntitySource );
		}
	}
