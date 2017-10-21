	public PropertyAuditingData(
			String name,
			String accessType,
			ModificationStore store,
			RelationTargetAuditMode relationTargetAuditMode,
			String auditMappedBy,
			String positionMappedBy,
			boolean forceInsertable,
			boolean syntheic,
			Value value) {
		this.name = name;
		this.beanName = name;
		this.accessType = accessType;
		this.store = store;
		this.relationTargetAuditMode = relationTargetAuditMode;
		this.auditMappedBy = auditMappedBy;
		this.positionMappedBy = positionMappedBy;
		this.forceInsertable = forceInsertable;
		this.syntheic = syntheic;
		this.value = value;
	}
