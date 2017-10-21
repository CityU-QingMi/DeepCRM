	@Override
	public void perform(
			Session session,
			String entityName,
			AuditEntitiesConfiguration auditEntitiesConfiguration,
			Serializable id,
			Object data,
			Object revision) {
		session.save( auditEntitiesConfiguration.getAuditEntityName( entityName ), data );
		sessionCacheCleaner.scheduleAuditDataRemoval( session, data );
	}
