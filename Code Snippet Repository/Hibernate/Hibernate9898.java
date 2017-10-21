	@SuppressWarnings({""})
	void addOneToOnePrimaryKeyJoinColumn(
			PropertyAuditingData propertyAuditingData,
			Value value,
			CompositeMapperBuilder mapper,
			String entityName,
			boolean insertable) {
		final String referencedEntityName = ( (ToOne) value ).getReferencedEntityName();

		final IdMappingData idMapping = mainGenerator.getReferencedIdMappingData(
				entityName,
				referencedEntityName,
				propertyAuditingData,
				true
		);

		final String lastPropertyPrefix = MappingTools.createToOneRelationPrefix( propertyAuditingData.getName() );

		// Generating the id mapper for the relation
		final IdMapper relMapper = idMapping.getIdMapper().prefixMappedProperties( lastPropertyPrefix );

		// Storing information about this relation
		mainGenerator.getEntitiesConfigurations().get( entityName ).addToOneRelation(
				propertyAuditingData.getName(), referencedEntityName, relMapper, insertable,
				MappingTools.ignoreNotFound( value )
		);

		// Adding mapper for the id
		final PropertyData propertyData = propertyAuditingData.getPropertyData();
		mapper.addComposite(
				propertyData,
				new OneToOnePrimaryKeyJoinColumnMapper(
						entityName,
						referencedEntityName,
						propertyData,
						mainGenerator.getServiceRegistry()
				)
		);
	}
