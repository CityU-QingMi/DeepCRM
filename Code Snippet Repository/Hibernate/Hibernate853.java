	public List<EntityHierarchySourceImpl> buildHierarchies() throws HibernateException {
		if ( toBeLinkedQueue != null && !toBeLinkedQueue.isEmpty() ) {
			if ( log.isDebugEnabled() ) {
				for ( Map.Entry<String, List<ExtendsQueueEntry>> waitingListEntry : toBeLinkedQueue.entrySet() ) {
					for ( ExtendsQueueEntry waitingEntry : waitingListEntry.getValue() ) {
						log.debugf(
								"Entity super-type named as extends [%s] for subclass [%s:%s] not found",
								waitingListEntry.getKey(),
								waitingEntry.sourceMappingDocument.getOrigin(),
								waitingEntry.sourceMappingDocument.determineEntityName( waitingEntry.jaxbSubEntityMapping )
						);
					}
				}
			}
			throw new HibernateException(
					"Not all named super-types (extends) were found : " + toBeLinkedQueue.keySet()
			);
		}

		return entityHierarchyList;
	}
