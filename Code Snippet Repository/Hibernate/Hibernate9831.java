	private void setAuditMappedByInsertable(
			String referencedEntityName,
			String entityName,
			ClassAuditingData referencedAuditData,
			PropertyAuditingData propertyAuditingData) {
		forcePropertyInsertable(
				referencedAuditData,
				propertyAuditingData.getAuditMappedBy(),
				entityName,
				referencedEntityName
		);

		forcePropertyInsertable(
				referencedAuditData,
				propertyAuditingData.getPositionMappedBy(),
				entityName,
				referencedEntityName
		);
	}
