	private AnnotationMetaAttribute createMetaCollectionAttribute(DeclaredType declaredType, Element element, String fqNameOfReturnType, String collection, String targetEntity) {
		if ( TypeUtils.containsAnnotation( element, Constants.ELEMENT_COLLECTION ) ) {
			String explicitTargetEntity = getTargetEntity( element.getAnnotationMirrors() );
			TypeMirror collectionElementType = TypeUtils.getCollectionElementType(
					declaredType, fqNameOfReturnType, explicitTargetEntity, context
			);
			final TypeElement collectionElement = (TypeElement) context.getTypeUtils()
					.asElement( collectionElementType );
			AccessTypeInformation accessTypeInfo = context.getAccessTypeInfo(
					collectionElementType.toString() );
			if ( accessTypeInfo == null ) {
				AccessType explicitAccessType = null;
				if ( collectionElement != null ) {
					explicitAccessType = TypeUtils.determineAnnotationSpecifiedAccessType(
						collectionElement
					);
				}
				accessTypeInfo = new AccessTypeInformation(
						collectionElementType.toString(),
						explicitAccessType,
						entity.getEntityAccessTypeInfo().getAccessType()
				);
				context.addAccessTypeInformation( collectionElementType.toString(), accessTypeInfo );
			}
			else {
				accessTypeInfo.setDefaultAccessType( entity.getEntityAccessTypeInfo().getAccessType() );
			}
		}
		if ( collection.equals( "javax.persistence.metamodel.MapAttribute" ) ) {
			return createAnnotationMetaAttributeForMap( declaredType, element, collection, targetEntity );
		}
		else {
			return new AnnotationMetaCollection(
					entity, element, collection, getElementType( declaredType, targetEntity )
			);
		}
	}
