	private void processWaitingSubEntityMappings(
			AbstractEntitySourceImpl entitySource,
			List<ExtendsQueueEntry> waitingList) {
		for ( ExtendsQueueEntry entry : waitingList ) {
			final SubclassEntitySourceImpl subEntitySource = createSubClassEntitySource(
					entry.sourceMappingDocument,
					entry.jaxbSubEntityMapping,
					entitySource
			);

			entitySourceByNameMap.put( subEntitySource.getEntityNamingSource().getEntityName(), subEntitySource );
			entitySource.add( subEntitySource );

			// this may have been a "middle type".  So link any sub entities that may be waiting on it
			linkAnyWaiting( entry.sourceMappingDocument, subEntitySource );

			processSubEntityElements( entry.sourceMappingDocument, entry.jaxbSubEntityMapping, subEntitySource );
		}
	}
