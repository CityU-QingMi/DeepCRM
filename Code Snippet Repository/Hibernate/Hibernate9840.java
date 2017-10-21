	private boolean checkPropertiesAudited(Iterator<Property> properties, ClassAuditingData auditingData) {
		while ( properties.hasNext() ) {
			final Property property = properties.next();
			final String propertyName = property.getName();
			final PropertyAuditingData propertyAuditingData = auditingData.getPropertyAuditingData( propertyName );
			if ( propertyAuditingData == null ) {
				return false;
			}
		}

		return true;
	}
