	private boolean fillPropertyData(
			XProperty property,
			PropertyAuditingData propertyData,
			String accessType,
			Audited allClassAudited) {

		// check if a property is declared as not audited to exclude it
		// useful if a class is audited but some properties should be excluded
		final NotAudited unVer = property.getAnnotation( NotAudited.class );
		if ( ( unVer != null
				&& !overriddenAuditedProperties.contains( property ) )
				|| overriddenNotAuditedProperties.contains( property ) ) {
			return false;
		}
		else {
			// if the optimistic locking field has to be unversioned and the current property
			// is the optimistic locking field, don't audit it
			if ( globalCfg.isDoNotAuditOptimisticLockingField() ) {
				final Version jpaVer = property.getAnnotation( Version.class );
				if ( jpaVer != null ) {
					return false;
				}
			}
		}

		final String propertyName = propertyNamePrefix + property.getName();
		if ( !this.checkAudited( property, propertyData,propertyName, allClassAudited, globalCfg.getModifiedFlagSuffix() ) ) {
			return false;
		}

		validateLobMappingSupport( property );

		propertyData.setName( propertyName );
		propertyData.setBeanName( property.getName() );
		propertyData.setAccessType( accessType );

		addPropertyJoinTables( property, propertyData );
		addPropertyAuditingOverrides( property, propertyData );
		if ( !processPropertyAuditingOverrides( property, propertyData ) ) {
			// not audited due to AuditOverride annotation
			return false;
		}
		addPropertyMapKey( property, propertyData );
		setPropertyAuditMappedBy( property, propertyData );
		setPropertyRelationMappedBy( property, propertyData );

		return true;
	}
