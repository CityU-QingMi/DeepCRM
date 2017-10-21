	private boolean processPropertyAuditingOverrides(XProperty property, PropertyAuditingData propertyData) {
		// if this property is part of a component, process all override annotations
		if ( this.auditedPropertiesHolder instanceof ComponentAuditingData ) {
			final List<AuditOverride> overrides = ( (ComponentAuditingData) this.auditedPropertiesHolder ).getAuditingOverrides();
			for ( AuditOverride override : overrides ) {
				if ( property.getName().equals( override.name() ) ) {
					// the override applies to this property
					if ( !override.isAudited() ) {
						return false;
					}
					else {
						if ( override.auditJoinTable() != null ) {
							propertyData.setJoinTable( override.auditJoinTable() );
						}
					}
				}
			}

		}
		return true;
	}
