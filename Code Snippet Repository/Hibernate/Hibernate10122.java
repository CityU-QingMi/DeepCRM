	@Override
	@SuppressWarnings({""})
	public void perform(Session session, Object revisionData) {
		final AuditEntitiesConfiguration entitiesCfg = enversService.getAuditEntitiesConfiguration();

		for ( PersistentCollectionChangeData persistentCollectionChangeData : collectionChanges ) {
			// Setting the revision number
			( (Map<String, Object>) persistentCollectionChangeData.getData().get( entitiesCfg.getOriginalIdPropName() ) )
					.put( entitiesCfg.getRevisionFieldName(), revisionData );

			auditStrategy.performCollectionChange(
					session,
					getEntityName(),
					referencingPropertyName,
					enversService,
					persistentCollectionChangeData,
					revisionData
			);
		}
	}
