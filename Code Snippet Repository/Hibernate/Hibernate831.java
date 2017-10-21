	protected AbstractSingularAttributeSourceEmbeddedImpl(
			final MappingDocument sourceMappingDocument,
			final AttributeSourceContainer container,
			final EmbeddedAttributeMapping embeddedAttributeMapping,
			List nestedAttributeMappings,
			boolean isDynamic,
			NaturalIdMutability naturalIdMutability,
			String logicalTableName) {
		this(
				sourceMappingDocument,
				embeddedAttributeMapping,
				new EmbeddableSourceImpl(
						sourceMappingDocument,
						new EmbeddableSourceContainer() {
							final AttributeRole role = container.getAttributeRoleBase().append(
									embeddedAttributeMapping.getName()
							);
							final AttributePath path = container.getAttributePathBase().append(
									embeddedAttributeMapping.getName()
							);
							final ToolingHintContext toolingHintContext = Helper.collectToolingHints(
									sourceMappingDocument.getToolingHintContext(),
									embeddedAttributeMapping
							);

							@Override
							public AttributeRole getAttributeRoleBase() {
								return role;
							}

							@Override
							public AttributePath getAttributePathBase() {
								return path;
							}

							@Override
							public ToolingHintContext getToolingHintContextBaselineForEmbeddable() {
								return toolingHintContext;
							}
						},
						embeddedAttributeMapping.getEmbeddableMapping(),
						nestedAttributeMappings,
						isDynamic,
						embeddedAttributeMapping.isUnique(),
						logicalTableName,
						naturalIdMutability
				),
				naturalIdMutability
		);
	}
