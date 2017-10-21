	private PluralAttributeMapKeySourceEmbeddedImpl(
			MappingDocument mappingDocument,
			final AbstractPluralAttributeSourceImpl pluralAttributeSource,
			EmbeddableMapping jaxbEmbeddable,
			List attributeMappings) {
		super( mappingDocument );
		this.embeddableSource = new EmbeddableSourceImpl(
				mappingDocument,
				new EmbeddableSourceContainer() {
					@Override
					public AttributeRole getAttributeRoleBase() {
						return pluralAttributeSource.getAttributeRole().append( "key" );
					}

					@Override
					public AttributePath getAttributePathBase() {
						return pluralAttributeSource.getAttributePath().append( "key" );
					}

					@Override
					public ToolingHintContext getToolingHintContextBaselineForEmbeddable() {
						return pluralAttributeSource.getToolingHintContext();
					}
				},
				jaxbEmbeddable,
				attributeMappings,
				false,
				false,
				null,
				NaturalIdMutability.NOT_NATURAL_ID
		);
	}
