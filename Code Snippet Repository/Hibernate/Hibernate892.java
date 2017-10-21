	public IdentifierSourceSimpleImpl(RootEntitySourceImpl rootEntitySource) {
		final JaxbHbmRootEntityType jaxbEntityMapping = rootEntitySource.jaxbEntityMapping();
		this.attribute = new SingularIdentifierAttributeSourceImpl(
				rootEntitySource.sourceMappingDocument(),
				rootEntitySource,
				jaxbEntityMapping.getId()
		);
		this.generatorDefinition = EntityHierarchySourceImpl.interpretGeneratorDefinition(
				rootEntitySource.sourceMappingDocument(),
				rootEntitySource.getEntityNamingSource(),
				rootEntitySource.jaxbEntityMapping().getId().getGenerator()
		);
		this.unsavedValue = jaxbEntityMapping.getId().getUnsavedValue();

		this.toolingHintContext = Helper.collectToolingHints(
				rootEntitySource.getToolingHintContext(),
				jaxbEntityMapping.getId()
		);
	}
