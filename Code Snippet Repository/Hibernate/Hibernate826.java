	protected AbstractPluralAttributeSourceImpl(
			MappingDocument mappingDocument,
			final PluralAttributeInfo pluralAttributeJaxbMapping,
			AttributeSourceContainer container) {
		super( mappingDocument );
		this.pluralAttributeJaxbMapping = pluralAttributeJaxbMapping;
		this.container = container;

		this.attributeRole = container.getAttributeRoleBase().append( pluralAttributeJaxbMapping.getName() );
		this.attributePath = container.getAttributePathBase().append( pluralAttributeJaxbMapping.getName() );

		this.keySource = new PluralAttributeKeySourceImpl(
				sourceMappingDocument(),
				pluralAttributeJaxbMapping.getKey(),
				container
		);

		this.typeInformation = new HibernateTypeSourceImpl( pluralAttributeJaxbMapping.getCollectionType() );

		this.caching = Helper.createCaching( pluralAttributeJaxbMapping.getCache() );

		this.filterSources = buildFilterSources( mappingDocument, pluralAttributeJaxbMapping );
		this.synchronizedTableNames = extractSynchronizedTableNames( pluralAttributeJaxbMapping );
		this.toolingHintContext = Helper.collectToolingHints(
				container.getToolingHintContext(),
				pluralAttributeJaxbMapping
		);

		this.elementSource = interpretElementType();

		this.fetchCharacteristics = FetchCharacteristicsPluralAttributeImpl.interpret(
				mappingDocument.getMappingDefaults(),
				pluralAttributeJaxbMapping.getFetch(),
				pluralAttributeJaxbMapping.getOuterJoin(),
				pluralAttributeJaxbMapping.getLazy(),
				pluralAttributeJaxbMapping.getBatchSize()
		);
	}
