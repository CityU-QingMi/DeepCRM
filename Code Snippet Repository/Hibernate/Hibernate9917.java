	private Audited computeAuditConfiguration(XClass clazz) {
		Audited allClassAudited = clazz.getAnnotation( Audited.class );
		// If processed class is not explicitly marked with @Audited annotation, check whether auditing is
		// forced by any of its child entities configuration (@AuditedOverride.forClass).
		if ( allClassAudited == null && overriddenAuditedClasses.contains( clazz ) ) {
			// Declared audited parent copies @Audited.modStore and @Audited.targetAuditMode configuration from
			// currently mapped entity.
			allClassAudited = persistentPropertiesSource.getXClass().getAnnotation( Audited.class );
			if ( allClassAudited == null ) {
				// If parent class declares @Audited on the field/property level.
				allClassAudited = DEFAULT_AUDITED;
			}
		}
		else if ( allClassAudited != null && overriddenNotAuditedClasses.contains( clazz ) ) {
			return null;
		}
		return allClassAudited;
	}
