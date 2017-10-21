	private PluralAttributeElementSource interpretElementType() {
		if ( pluralAttributeJaxbMapping.getElement() != null ) {
			return new PluralAttributeElementSourceBasicImpl(
					sourceMappingDocument(),
					this,
					pluralAttributeJaxbMapping.getElement()
			);
		}
		else if ( pluralAttributeJaxbMapping.getCompositeElement() != null ) {
			return new PluralAttributeElementSourceEmbeddedImpl(
					sourceMappingDocument(),
					this,
					pluralAttributeJaxbMapping.getCompositeElement()
			);
		}
		else if ( pluralAttributeJaxbMapping.getOneToMany() != null ) {
			return new PluralAttributeElementSourceOneToManyImpl(
					sourceMappingDocument(),
					this,
					pluralAttributeJaxbMapping.getOneToMany(),
					pluralAttributeJaxbMapping.getCascade()
			);
		}
		else if ( pluralAttributeJaxbMapping.getManyToMany() != null ) {
			return new PluralAttributeElementSourceManyToManyImpl(
					sourceMappingDocument(),
					this,
					pluralAttributeJaxbMapping.getManyToMany()
			);
		}
		else if ( pluralAttributeJaxbMapping.getManyToAny() != null ) {
			return new PluralAttributeElementSourceManyToAnyImpl(
					sourceMappingDocument(),
					this,
					pluralAttributeJaxbMapping.getManyToAny(),
					pluralAttributeJaxbMapping.getCascade()
			);
		}
		else {
			throw new MappingException(
					"Unexpected collection element type : " + pluralAttributeJaxbMapping.getName(),
					sourceMappingDocument().getOrigin()
			);
		}
	}
