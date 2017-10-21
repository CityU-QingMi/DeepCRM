	private void updateCalculatedProperty(String entityName, Property property, String propertyName, AuditedPropertiesHolder propertyHolder) {
		final PropertyAuditingData propertyAuditingData = propertyHolder.getPropertyAuditingData( propertyName );
		final boolean isAuditMappedBy = propertyAuditingData.getAuditMappedBy() != null;
		final boolean isRelationMappedBy = propertyAuditingData.getRelationMappedBy() != null;

		// handle updating the property, if applicable.
		if ( isAuditMappedBy || isRelationMappedBy ) {
			final String referencedEntityName = MappingTools.getReferencedEntityName( property.getValue() );
			final ClassAuditingData referencedAuditData = entityNameToAuditingData.get( referencedEntityName );

			if ( isAuditMappedBy ) {
				// If a property had the @AuditMappedBy annotation, setting the referenced fields to be always insertable.
				setAuditMappedByInsertable( referencedEntityName, entityName, referencedAuditData, propertyAuditingData );
			}
			else if ( isRelationMappedBy && ( property.getValue() instanceof List ) ) {
				// If a property has mappedBy= and @Indexed and isn't @AuditMappedBy, add synthetic support.
				addSyntheticIndexProperty(
						(List) property.getValue(),
						property.getPropertyAccessorName(),
						referencedAuditData
				);
			}
		}

		// HHH-9108
		// Added support to handle nested property calculations for components.
		// This is useful for AuditMappedBy inside an Embeddable that holds a collection of entities.
		if ( propertyAuditingData instanceof ComponentAuditingData ) {
			final ComponentAuditingData componentAuditingData = ( ComponentAuditingData) propertyAuditingData;
			final Component component = (Component) property.getValue();
			for ( String componentPropertyName : componentAuditingData.getNonSyntheticPropertyNames() ) {
				final Property componentProperty = component.getProperty( componentPropertyName );
				updateCalculatedProperty( entityName, componentProperty, componentPropertyName, componentAuditingData );
			}
		}
	}
