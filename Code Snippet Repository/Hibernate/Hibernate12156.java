	@Override
	public AnnotationMetaAttribute visitDeclared(DeclaredType declaredType, Element element) {
		AnnotationMetaAttribute metaAttribute = null;
		TypeElement returnedElement = (TypeElement) context.getTypeUtils().asElement( declaredType );
		// WARNING: .toString() is necessary here since Name equals does not compare to String
		String fqNameOfReturnType = returnedElement.getQualifiedName().toString();
		String collection = Constants.COLLECTIONS.get( fqNameOfReturnType );
		String targetEntity = getTargetEntity( element.getAnnotationMirrors() );
		if ( collection != null ) {
			return createMetaCollectionAttribute(
					declaredType, element, fqNameOfReturnType, collection, targetEntity
			);
		}
		else if ( isBasicAttribute( element, returnedElement ) ) {
			String type = targetEntity != null ? targetEntity : returnedElement.getQualifiedName().toString();
			return new AnnotationMetaSingleAttribute( entity, element, type );
		}
		return metaAttribute;
	}
