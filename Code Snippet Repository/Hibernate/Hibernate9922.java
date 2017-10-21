	@Override
	protected boolean checkAudited(
			XProperty property,
			PropertyAuditingData propertyData, String propertyName,
			Audited allClassAudited, String modifiedFlagSuffix) {
		// Checking if this property is explicitly audited or if all properties are.
		final Audited aud = property.getAnnotation( Audited.class );
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
		}
		else {
			propertyData.setStore( ModificationStore.FULL );
		}
		return true;
	}
