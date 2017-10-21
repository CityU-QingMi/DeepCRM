	private void addProperties(
			Element parent,
			Iterator<Property> properties,
			CompositeMapperBuilder currentMapper,
			ClassAuditingData auditingData,
			String entityName,
			EntityXmlMappingData xmlMappingData,
			boolean firstPass) {
		while ( properties.hasNext() ) {
			final Property property = properties.next();
			final String propertyName = property.getName();
			final PropertyAuditingData propertyAuditingData = auditingData.getPropertyAuditingData( propertyName );
			if ( propertyAuditingData != null ) {
				// HHH-10246
				// Verifies if a mapping exists using a @JoinColumn against a @NaturalId field
				// and if so, this eliminates the mapping property as it isn't needed.
				if ( property instanceof SyntheticProperty ) {
					if ( property.getValue().isAlternateUniqueKey() ) {
						continue;
					}
				}
				addValue(
						parent,
						property.getValue(),
						currentMapper,
						entityName,
						xmlMappingData,
						propertyAuditingData,
						isPropertyInsertable( property ),
						firstPass,
						true
				);
			}
		}
	}
