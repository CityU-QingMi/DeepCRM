	private void addPropertyJoinTables(XProperty property, PropertyAuditingData propertyData) {
		// first set the join table based on the AuditJoinTable annotation
		final AuditJoinTable joinTable = property.getAnnotation( AuditJoinTable.class );
		if ( joinTable != null ) {
			propertyData.setJoinTable( joinTable );
		}
		else {
			propertyData.setJoinTable( DEFAULT_AUDIT_JOIN_TABLE );
		}
	}
