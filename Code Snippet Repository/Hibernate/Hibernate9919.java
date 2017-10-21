	private void addPropertiesFromClass(XClass clazz) {
		final Audited allClassAudited = computeAuditConfiguration( clazz );

		//look in the class
		addFromProperties(
				clazz.getDeclaredProperties( "field" ),
				"field",
				fieldAccessedPersistentProperties,
				allClassAudited
		);
		addFromProperties(
				clazz.getDeclaredProperties( "property" ),
				"property",
				propertyAccessedPersistentProperties,
				allClassAudited
		);

		if ( allClassAudited != null || !auditedPropertiesHolder.isEmpty() ) {
			final XClass superclazz = clazz.getSuperclass();
			if ( !clazz.isInterface() && !"java.lang.Object".equals( superclazz.getName() ) ) {
				addPropertiesFromClass( superclazz );
			}
		}
	}
