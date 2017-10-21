	protected boolean checkAudited(
			XProperty property,
			PropertyAuditingData propertyData, String propertyName,
			Audited allClassAudited, String modifiedFlagSuffix) {
		// Checking if this property is explicitly audited or if all properties are.
		Audited aud = ( property.isAnnotationPresent( Audited.class ) )
				? property.getAnnotation( Audited.class )
				: allClassAudited;
		if ( aud == null
				&& overriddenAuditedProperties.contains( property )
				&& !overriddenNotAuditedProperties.contains( property ) ) {
			// Assigning @Audited defaults. If anyone needs to customize those values in the future,
			// appropriate fields shall be added to @AuditOverride annotation.
			aud = DEFAULT_AUDITED;
		}
		if ( aud != null ) {
			propertyData.setStore( aud.modStore() );
			propertyData.setRelationTargetAuditMode( aud.targetAuditMode() );
			propertyData.setUsingModifiedFlag( checkUsingModifiedFlag( aud ) );
			if( aud.modifiedColumnName() != null && !"".equals( aud.modifiedColumnName() ) ) {
				propertyData.setModifiedFlagName( aud.modifiedColumnName() );
			}
			else {
				propertyData.setModifiedFlagName(
						MetadataTools.getModifiedFlagPropertyName( propertyName, modifiedFlagSuffix )
				);
			}
			return true;
		}
		else {
			return false;
		}
	}
