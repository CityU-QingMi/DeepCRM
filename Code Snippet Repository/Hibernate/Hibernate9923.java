	public PropertyAuditingData(
			String name, String accessType, ModificationStore store,
			RelationTargetAuditMode relationTargetAuditMode,
			String auditMappedBy, String positionMappedBy,
			boolean forceInsertable) {
		this(
				name,
				accessType,
				store,
				relationTargetAuditMode,
				auditMappedBy,
				positionMappedBy,
				forceInsertable,
				false,
				null
		);
	}
