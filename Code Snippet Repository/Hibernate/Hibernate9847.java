	private void addSynthetics(
			Element classMapping,
			ClassAuditingData auditingData,
			CompositeMapperBuilder currentMapper,
			EntityXmlMappingData xmlMappingData,
			String entityName,
			boolean firstPass) {
		for ( PropertyAuditingData propertyAuditingData : auditingData.getSyntheticProperties() ) {
			addValue(
					classMapping,
					propertyAuditingData.getValue(),
					currentMapper,
					entityName,
					xmlMappingData,
					propertyAuditingData,
					true,
					firstPass,
					false
			);
		}
	}
