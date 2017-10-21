	@Deprecated
	default void performCollectionChange(
			Session session,
			String entityName,
			String propertyName,
			EnversService enversService,
			PersistentCollectionChangeData persistentCollectionChangeData,
			Object revision) {
		performCollectionChange(
				session,
				entityName,
				propertyName,
				enversService.getAuditEntitiesConfiguration(),
				persistentCollectionChangeData,
				revision
		);
	}
