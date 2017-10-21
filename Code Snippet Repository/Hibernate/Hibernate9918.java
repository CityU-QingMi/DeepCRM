	private void addPropertiesFromDynamicComponent(DynamicComponentSource dynamicComponentSource) {
		Audited audited = computeAuditConfiguration( dynamicComponentSource.getXClass() );
		if ( !fieldAccessedPersistentProperties.isEmpty() ) {
			throw new MappingException(
					"Audited dynamic component cannot have properties with access=\"field\" for properties: " + fieldAccessedPersistentProperties + ". \n Change properties access=\"property\", to make it work)"
			);
		}
		for ( String property : propertyAccessedPersistentProperties ) {
			String accessType = AccessType.PROPERTY.getType();
			if ( !auditedPropertiesHolder.contains( property ) ) {
				final Value propertyValue = persistentPropertiesSource.getProperty( property ).getValue();
				if ( propertyValue instanceof Component ) {
					this.addFromComponentProperty(
							new DynamicProperty( dynamicComponentSource, property ),
							accessType,
							(Component) propertyValue,
							audited
					);
				}
				else {
					this.addFromNotComponentProperty(
							new DynamicProperty( dynamicComponentSource, property ),
							accessType,
							audited
					);
				}
			}
		}
	}
