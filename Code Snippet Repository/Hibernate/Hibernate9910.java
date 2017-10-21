	private void addPropertyAuditingOverrides(XProperty property, PropertyAuditingData propertyData) {
		final AuditOverride annotationOverride = property.getAnnotation( AuditOverride.class );
		if ( annotationOverride != null ) {
			propertyData.addAuditingOverride( annotationOverride );
		}
		final AuditOverrides annotationOverrides = property.getAnnotation( AuditOverrides.class );
		if ( annotationOverrides != null ) {
			propertyData.addAuditingOverrides( annotationOverrides );
		}
	}
