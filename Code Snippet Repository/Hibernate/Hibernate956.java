	public PluralAttributeSourcePrimitiveArrayImpl(
			MappingDocument sourceMappingDocument,
			JaxbHbmPrimitiveArrayType jaxbArrayMapping,
			AttributeSourceContainer container) {
		super( sourceMappingDocument, jaxbArrayMapping, container );
		this.jaxbArrayMapping = jaxbArrayMapping;
		if ( jaxbArrayMapping.getListIndex() != null ) {
			this.indexSource = new PluralAttributeSequentialIndexSourceImpl( sourceMappingDocument(), jaxbArrayMapping.getListIndex() );
		}
		else {
			this.indexSource = new PluralAttributeSequentialIndexSourceImpl( sourceMappingDocument(), jaxbArrayMapping.getIndex() );
		}
	}
