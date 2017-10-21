	protected AbstractAuditWorkUnit(
			SessionImplementor sessionImplementor,
			String entityName,
			EnversService enversService,
			Serializable id,
			RevisionType revisionType) {
		this.sessionImplementor = sessionImplementor;
		this.enversService = enversService;
		this.id = id;
		this.entityName = entityName;
		this.revisionType = revisionType;
		this.auditStrategy = enversService.getAuditStrategy();
	}
