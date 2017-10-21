	private void addFromProperties(
			Iterable<XProperty> properties,
			String accessType,
			Set<String> persistentProperties,
			Audited allClassAudited) {
		for ( XProperty property : properties ) {
			// If this is not a persistent property, with the same access type as currently checked,
			// it's not audited as well.
			// If the property was already defined by the subclass, is ignored by superclasses
			if ( persistentProperties.contains( property.getName() )
					&& !auditedPropertiesHolder.contains( property.getName() ) ) {
				final Value propertyValue = persistentPropertiesSource.getProperty( property.getName() ).getValue();
				if ( propertyValue instanceof Component ) {
					this.addFromComponentProperty( property, accessType, (Component) propertyValue, allClassAudited );
				}
				else {
					this.addFromNotComponentProperty( property, accessType, allClassAudited );
				}
			}
			else if ( propertiesGroupMapping.containsKey( property.getName() ) ) {
				// Retrieve embedded component name based on class field.
				final String embeddedName = propertiesGroupMapping.get( property.getName() );
				if ( !auditedPropertiesHolder.contains( embeddedName ) ) {
					// Manage properties mapped within <properties> tag.
					final Value propertyValue = persistentPropertiesSource.getProperty( embeddedName ).getValue();
					this.addFromPropertiesGroup(
							embeddedName,
							property,
							accessType,
							(Component) propertyValue,
							allClassAudited
					);
				}
			}
		}
	}
