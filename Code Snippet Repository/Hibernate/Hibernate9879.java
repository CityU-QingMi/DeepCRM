	private void generateSecondPass(String entityName, Component component) {
		Iterator properties = component.getPropertyIterator();
		while ( properties.hasNext() ) {
			final Property property = (Property) properties.next();
			if ( property.getValue() instanceof ToOne ) {
				final PropertyAuditingData propertyData = getIdPersistentPropertyAuditingData( property );
				final String referencedEntityName = ( (ToOne) property.getValue() ).getReferencedEntityName();

				final String prefix = mainGenerator.getVerEntCfg().getOriginalIdPropName() + "." + propertyData.getName();
				final IdMapper relMapper = mainGenerator.getEntitiesConfigurations().get( referencedEntityName ).getIdMapper();
				final IdMapper prefixedMapper = relMapper.prefixMappedProperties( prefix + "." );

				mainGenerator.getEntitiesConfigurations().get( entityName ).addToOneRelation(
						prefix,
						referencedEntityName,
						prefixedMapper,
						true,
						false
				);
			}
		}
	}
