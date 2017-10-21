	public IdentifierSourceAggregatedCompositeImpl(final RootEntitySourceImpl rootEntitySource) {
		final EmbeddedAttributeMappingAdapterAggregatedCompositeId compositeIdAdapter =
				new EmbeddedAttributeMappingAdapterAggregatedCompositeId( rootEntitySource );

		this.attributeSource = new SingularAttributeSourceAggregatedCompositeIdentifierImpl(
				rootEntitySource.sourceMappingDocument(),
				compositeIdAdapter
		);
		this.generatorDefinition = EntityHierarchySourceImpl.interpretGeneratorDefinition(
				rootEntitySource.sourceMappingDocument(),
				rootEntitySource.getEntityNamingSource(),
				rootEntitySource.jaxbEntityMapping().getCompositeId().getGenerator()
		);

		this.toolingHintContext = Helper.collectToolingHints(
				rootEntitySource.getToolingHintContext(),
				rootEntitySource.jaxbEntityMapping().getCompositeId()
		);

	}
