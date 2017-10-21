	private void forcePropertyInsertable(
			ClassAuditingData classAuditingData, String propertyName,
			String entityName, String referencedEntityName) {
		if ( propertyName != null ) {
			if ( classAuditingData.getPropertyAuditingData( propertyName ) == null ) {
				throw new MappingException(
						"@AuditMappedBy points to a property that doesn't exist: " +
								referencedEntityName + "." + propertyName
				);
			}

			LOG.debugf(
					"Non-insertable property %s.%s will be made insertable because a matching @AuditMappedBy was found in the %s entity",
					referencedEntityName,
					propertyName,
					entityName
			);

			classAuditingData
					.getPropertyAuditingData( propertyName )
					.setForceInsertable( true );
		}
	}
