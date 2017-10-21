	public EmbeddableSourceVirtualImpl(
			MappingDocument mappingDocument,
			final AttributesHelper.Callback containingCallback,
			EmbeddableSourceContainer container,
			List attributeJaxbMappings,
			String logicalTableName,
			NaturalIdMutability naturalIdMutability,
			JaxbHbmPropertiesType jaxbPropertiesGroup) {
		super( mappingDocument );
		this.attributeRoleBase = container.getAttributeRoleBase();
		this.attributePathBase = container.getAttributePathBase();

		this.attributeSources = new ArrayList<AttributeSource>();
		AttributesHelper.processAttributes(
				mappingDocument,
				new AttributesHelper.Callback() {
					@Override
					public AttributeSourceContainer getAttributeSourceContainer() {
						return EmbeddableSourceVirtualImpl.this;
					}

					@Override
					public void addAttributeSource(AttributeSource attributeSource) {
						attributeSources.add( attributeSource );
					}
				},
				attributeJaxbMappings,
				logicalTableName,
				naturalIdMutability
		);

		this.isUnique = jaxbPropertiesGroup.isUnique();

		this.toolingHintContext = container.getToolingHintContextBaselineForEmbeddable();
	}
