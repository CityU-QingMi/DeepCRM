	private List<AuditOverride> computeAuditOverrides(XClass clazz) {
		final AuditOverrides auditOverrides = clazz.getAnnotation( AuditOverrides.class );
		final AuditOverride auditOverride = clazz.getAnnotation( AuditOverride.class );
		if ( auditOverrides == null && auditOverride != null ) {
			return Arrays.asList( auditOverride );
		}
		else if ( auditOverrides != null && auditOverride == null ) {
			return Arrays.asList( auditOverrides.value() );
		}
		else if ( auditOverrides != null && auditOverride != null ) {
			throw new MappingException(
					"@AuditOverrides annotation should encapsulate all @AuditOverride declarations. " +
							"Please revise Envers annotations applied to class " + clazz.getName() + "."
			);
		}
		return Collections.emptyList();
	}
