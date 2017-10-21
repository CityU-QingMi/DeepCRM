	private void addModifiedFlagIfNeeded(
			Element parent,
			PropertyAuditingData propertyAuditingData,
			boolean processModifiedFlag) {
		if ( processModifiedFlag && propertyAuditingData.isUsingModifiedFlag() ) {
			MetadataTools.addModifiedFlagProperty(
					parent,
					propertyAuditingData.getName(),
					globalCfg.getModifiedFlagSuffix(),
					propertyAuditingData.getModifiedFlagName()
			);
		}
	}
