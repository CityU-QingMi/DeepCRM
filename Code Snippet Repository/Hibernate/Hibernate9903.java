	private void addFromPropertiesGroup(
			String embeddedName,
			XProperty property,
			String accessType,
			Component propertyValue,
			Audited allClassAudited) {
		final ComponentAuditingData componentData = new ComponentAuditingData();
		final boolean isAudited = fillPropertyData( property, componentData, accessType, allClassAudited );
		if ( isAudited ) {
			// EntityPersister.getPropertyNames() returns name of embedded component instead of class field.
			componentData.setName( embeddedName );
			// Marking component properties as placed directly in class (not inside another component).
			componentData.setBeanName( null );

			final PersistentPropertiesSource componentPropertiesSource = new ComponentPropertiesSource(
					reflectionManager,
					propertyValue
			);
			final AuditedPropertiesReader audPropReader = new AuditedPropertiesReader(
					ModificationStore.FULL, componentPropertiesSource, componentData, globalCfg, reflectionManager,
					propertyNamePrefix + MappingTools.createComponentPrefix( embeddedName )
			);
			audPropReader.read();

			auditedPropertiesHolder.addPropertyAuditingData( embeddedName, componentData );
		}
	}
