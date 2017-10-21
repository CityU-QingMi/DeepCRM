	protected final void generateBidirectionalCollectionChangeWorkUnits(
			AuditProcess auditProcess,
			EntityPersister entityPersister,
			String entityName,
			Object[] newState,
			Object[] oldState,
			SessionImplementor session) {
		// Checking if this is enabled in configuration ...
		if ( !enversService.getGlobalConfiguration().isGenerateRevisionsForCollections() ) {
			return;
		}

		// Checks every property of the entity, if it is an "owned" to-one relation to another entity.
		// If the value of that property changed, and the relation is bi-directional, a new revision
		// for the related entity is generated.
		final String[] propertyNames = entityPersister.getPropertyNames();

		for ( int i = 0; i < propertyNames.length; i++ ) {
			final String propertyName = propertyNames[i];
			final RelationDescription relDesc = enversService.getEntitiesConfigurations().getRelationDescription(
					entityName,
					propertyName
			);
			if ( relDesc != null && relDesc.isBidirectional() && relDesc.getRelationType() == RelationType.TO_ONE &&
					relDesc.isInsertable() ) {
				// Checking for changes
				final Object oldValue = oldState == null ? null : oldState[i];
				final Object newValue = newState == null ? null : newState[i];

				if ( !EntityTools.entitiesEqual( session, relDesc.getToEntityName(), oldValue, newValue ) ) {
					// We have to generate changes both in the old collection (size decreses) and new collection
					// (size increases).
					if ( newValue != null ) {
						addCollectionChangeWorkUnit( auditProcess, session, entityName, relDesc, newValue );
					}

					if ( oldValue != null ) {
						addCollectionChangeWorkUnit( auditProcess, session, entityName, relDesc, oldValue );
					}
				}
			}
		}
	}
