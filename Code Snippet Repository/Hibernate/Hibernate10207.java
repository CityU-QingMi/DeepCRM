	@Override
	public void performCollectionChange(
			Session session,
			String entityName,
			String propertyName,
			AuditEntitiesConfiguration auditEntitiesConfiguration,
			PersistentCollectionChangeData persistentCollectionChangeData,
			Object revision) {
		session.save( persistentCollectionChangeData.getEntityName(), persistentCollectionChangeData.getData() );
		sessionCacheCleaner.scheduleAuditDataRemoval( session, persistentCollectionChangeData.getData() );
	}
