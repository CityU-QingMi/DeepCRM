	public PluralAttributeSourceListImpl(
			MappingDocument sourceMappingDocument,
			JaxbHbmListType jaxbListMapping,
			AttributeSourceContainer container) {
		super( sourceMappingDocument, jaxbListMapping, container );
		this.jaxbListMapping = jaxbListMapping;
		if ( jaxbListMapping.getListIndex() != null ) {
			this.indexSource = new PluralAttributeSequentialIndexSourceImpl( sourceMappingDocument(), jaxbListMapping.getListIndex() );
		}
		else {
			this.indexSource = new PluralAttributeSequentialIndexSourceImpl( sourceMappingDocument(), jaxbListMapping.getIndex() );
		}
	}
