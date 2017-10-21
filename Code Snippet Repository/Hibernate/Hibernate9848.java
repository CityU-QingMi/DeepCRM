	@SuppressWarnings({""})
	public void generateSecondPass(
			PersistentClass pc,
			ClassAuditingData auditingData,
			EntityXmlMappingData xmlMappingData) {
		final String entityName = pc.getEntityName();
		LOG.debugf( "Generating second-pass auditing mapping for entity %s", entityName );

		final CompositeMapperBuilder propertyMapper = entitiesConfigurations.get( entityName ).getPropertyMapper();

		// Mapping unjoined properties
		final Element parent = xmlMappingData.getClassMapping();

		// HHH-11748 - Generate a second pass for identifiers
		// This is useful for situations where @Id point to @ManyToOne and @OneToOne associations.
		idMetadataGenerator.generateSecondPass( entityName, pc );

		addProperties(
				parent,
				pc.getUnjoinedPropertyIterator(),
				propertyMapper,
				auditingData,
				entityName,
				xmlMappingData,
				false
		);

		// Mapping joins (second pass)
		addJoins( pc, propertyMapper, auditingData, entityName, xmlMappingData, false );
	}
