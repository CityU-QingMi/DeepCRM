	public PluralAttributeElementSourceOneToManyImpl(
			MappingDocument mappingDocument,
			final PluralAttributeSource pluralAttributeSource,
			final JaxbHbmOneToManyCollectionElementType jaxbOneToManyElement,
			String cascadeString) {
		super( mappingDocument, pluralAttributeSource );
		this.jaxbOneToManyElement = jaxbOneToManyElement;
		this.cascadeString = cascadeString;

		this.referencedEntityName = StringHelper.isNotEmpty( jaxbOneToManyElement.getEntityName() )
				? jaxbOneToManyElement.getEntityName()
				: mappingDocument.qualifyClassName( jaxbOneToManyElement.getClazz() );
	}
