	private void addFromComponentProperty(
			XProperty property,
			String accessType,
			Component propertyValue,
			Audited allClassAudited) {
		final ComponentAuditingData componentData = new ComponentAuditingData();
		final boolean isAudited = fillPropertyData( property, componentData, accessType, allClassAudited );

		final PersistentPropertiesSource componentPropertiesSource;
		if ( propertyValue.isDynamic() ) {
			componentPropertiesSource = new DynamicComponentSource( reflectionManager, propertyValue, property );
		}
		else {
			componentPropertiesSource = new ComponentPropertiesSource( reflectionManager, propertyValue );
		}

		final ComponentAuditedPropertiesReader audPropReader = new ComponentAuditedPropertiesReader(
				ModificationStore.FULL,
				componentPropertiesSource,
				componentData,
				globalCfg,
				reflectionManager,
				propertyNamePrefix + MappingTools.createComponentPrefix( property.getName() )
		);
		audPropReader.read();

		if ( isAudited ) {
			// Now we know that the property is audited
			auditedPropertiesHolder.addPropertyAuditingData( property.getName(), componentData );
		}
	}
