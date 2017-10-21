	@SuppressWarnings({""})
	void addOneToOneNotOwning(
			PropertyAuditingData propertyAuditingData,
			Value value,
			CompositeMapperBuilder mapper,
			String entityName) {
		final OneToOne propertyValue = (OneToOne) value;
		final String owningReferencePropertyName = propertyValue.getReferencedPropertyName();

		final EntityConfiguration configuration = mainGenerator.getEntitiesConfigurations().get( entityName );
		if ( configuration == null ) {
			throw new MappingException( "An audited relation to a non-audited entity " + entityName + "!" );
		}

		final IdMappingData ownedIdMapping = configuration.getIdMappingData();

		if ( ownedIdMapping == null ) {
			throw new MappingException( "An audited relation to a non-audited entity " + entityName + "!" );
		}

		final String lastPropertyPrefix = MappingTools.createToOneRelationPrefix( owningReferencePropertyName );
		final String referencedEntityName = propertyValue.getReferencedEntityName();

		// Generating the id mapper for the relation
		final IdMapper ownedIdMapper = ownedIdMapping.getIdMapper().prefixMappedProperties( lastPropertyPrefix );

		// Storing information about this relation
		mainGenerator.getEntitiesConfigurations().get( entityName ).addToOneNotOwningRelation(
				propertyAuditingData.getName(), owningReferencePropertyName, referencedEntityName,
				ownedIdMapper, MappingTools.ignoreNotFound( value )
		);

		// Adding mapper for the id
		final PropertyData propertyData = propertyAuditingData.getPropertyData();
		mapper.addComposite(
				propertyData,
				new OneToOneNotOwningMapper(
						entityName,
						referencedEntityName,
						owningReferencePropertyName,
						propertyData,
						mainGenerator.getServiceRegistry()
				)
		);
	}
