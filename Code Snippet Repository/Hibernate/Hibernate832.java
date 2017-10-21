	public AbstractSingularAttributeSourceEmbeddedImpl(
			MappingDocument sourceMappingDocument,
			EmbeddedAttributeMapping jaxbEmbeddedAttributeMapping,
			EmbeddableSource embeddableSource,
			NaturalIdMutability naturalIdMutability) {
		super( sourceMappingDocument );
		this.jaxbEmbeddedAttributeMapping = jaxbEmbeddedAttributeMapping;
		this.embeddableSource = embeddableSource;
		this.naturalIdMutability = naturalIdMutability;
	}
