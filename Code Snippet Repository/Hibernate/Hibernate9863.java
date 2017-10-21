	public CollectionMetadataGenerator(
			AuditMetadataGenerator mainGenerator,
			Collection propertyValue,
			CompositeMapperBuilder currentMapper,
			String referencingEntityName,
			EntityXmlMappingData xmlMappingData,
			PropertyAuditingData propertyAuditingData) {
		this.mainGenerator = mainGenerator;
		this.propertyValue = propertyValue;
		this.currentMapper = currentMapper;
		this.referencingEntityName = referencingEntityName;
		this.xmlMappingData = xmlMappingData;
		this.propertyAuditingData = propertyAuditingData;

		this.propertyName = propertyAuditingData.getName();

		referencingEntityConfiguration = mainGenerator.getEntitiesConfigurations().get( referencingEntityName );
		if ( referencingEntityConfiguration == null ) {
			throw new MappingException( "Unable to read auditing configuration for " + referencingEntityName + "!" );
		}

		referencedEntityName = MappingTools.getReferencedEntityName( propertyValue.getElement() );
	}
