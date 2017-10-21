	private void linkAnyWaiting(
			MappingDocument mappingDocument,
			AbstractEntitySourceImpl entitySource) {
		if ( toBeLinkedQueue == null ) {
			return;
		}

		List<ExtendsQueueEntry> waitingList = toBeLinkedQueue.remove( entitySource.jaxbEntityMapping().getEntityName() );
		if ( waitingList != null ) {
			processWaitingSubEntityMappings( entitySource, waitingList );
			waitingList.clear();
		}

		if ( StringHelper.isNotEmpty( entitySource.jaxbEntityMapping().getName() ) ) {
			final String entityClassName = entitySource.jaxbEntityMapping().getName();
			waitingList = toBeLinkedQueue.remove( entityClassName );
			if ( waitingList != null ) {
				processWaitingSubEntityMappings( entitySource, waitingList );
				waitingList.clear();
			}

			final String qualifiedEntityClassName = mappingDocument.qualifyClassName( entityClassName );
			if ( !entityClassName.equals( qualifiedEntityClassName ) ) {
				waitingList = toBeLinkedQueue.remove( qualifiedEntityClassName );
				if ( waitingList != null ) {
					processWaitingSubEntityMappings( entitySource, waitingList );
					waitingList.clear();
				}
			}
		}
	}
