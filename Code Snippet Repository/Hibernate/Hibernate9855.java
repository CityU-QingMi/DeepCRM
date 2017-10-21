	void addValue(
			Element parent, Value value, CompositeMapperBuilder currentMapper, String entityName,
			EntityXmlMappingData xmlMappingData, PropertyAuditingData propertyAuditingData,
			boolean insertable, boolean firstPass, boolean processModifiedFlag) {
		if ( firstPass ) {
			addValueInFirstPass(
					parent, value, currentMapper, entityName,
					xmlMappingData, propertyAuditingData, insertable, processModifiedFlag
			);
		}
		else {
			addValueInSecondPass(
					parent, value, currentMapper, entityName,
					xmlMappingData, propertyAuditingData, insertable, processModifiedFlag
			);
		}
	}
