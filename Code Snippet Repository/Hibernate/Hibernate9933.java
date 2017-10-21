	private void generateBidirectionalCollectionChangeWorkUnits(
			AuditProcess auditProcess,
			AbstractCollectionEvent event,
			PersistentCollectionChangeWorkUnit workUnit,
			RelationDescription rd) {
		// Checking if this is enabled in configuration ...
		if ( !getEnversService().getGlobalConfiguration().isGenerateRevisionsForCollections() ) {
			return;
		}

		// Checking if this is not a bidirectional relation - then, a revision needs also be generated for
		// the other side of the relation.
		// relDesc can be null if this is a collection of simple values (not a relation).
		if ( rd != null && rd.isBidirectional() ) {
			final String relatedEntityName = rd.getToEntityName();
			final IdMapper relatedIdMapper = getEnversService().getEntitiesConfigurations().get( relatedEntityName ).getIdMapper();

			final Set<String> toPropertyNames = getEnversService().getEntitiesConfigurations().getToPropertyNames(
					event.getAffectedOwnerEntityName(),
					rd.getFromPropertyName(),
					relatedEntityName
			);
			final String toPropertyName = toPropertyNames.iterator().next();

			for ( PersistentCollectionChangeData changeData : workUnit.getCollectionChanges() ) {
				final Object relatedObj = changeData.getChangedElement();
				final Serializable relatedId = (Serializable) relatedIdMapper.mapToIdFromEntity( relatedObj );

				auditProcess.addWorkUnit(
						new CollectionChangeWorkUnit(
								event.getSession(),
								event.getSession().bestGuessEntityName( relatedObj ),
								toPropertyName,
								getEnversService(),
								relatedId,
								relatedObj
						)
				);
			}
		}
	}
