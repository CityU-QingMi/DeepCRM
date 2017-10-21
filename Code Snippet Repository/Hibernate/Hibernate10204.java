	@Deprecated
	default void perform(
			Session session,
			String entityName,
			EnversService enversService,
			Serializable id,
			Object data,
			Object revision) {
		perform(
				session,
				entityName,
				enversService.getAuditEntitiesConfiguration(),
				id,
				data,
				revision
		);
	}
