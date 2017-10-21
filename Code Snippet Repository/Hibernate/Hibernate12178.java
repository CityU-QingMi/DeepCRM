	public static TypeMirror getCollectionElementType(DeclaredType t, String fqNameOfReturnedType, String explicitTargetEntityName, Context context) {
		TypeMirror collectionElementType;
		if ( explicitTargetEntityName != null ) {
			Elements elements = context.getElementUtils();
			TypeElement element = elements.getTypeElement( explicitTargetEntityName );
			collectionElementType = element.asType();
		}
		else {
			List<? extends TypeMirror> typeArguments = t.getTypeArguments();
			if ( typeArguments.size() == 0 ) {
				throw new MetaModelGenerationException( "Unable to determine collection type" );
			}
			else if ( Map.class.getCanonicalName().equals( fqNameOfReturnedType ) ) {
				collectionElementType = t.getTypeArguments().get( 1 );
			}
			else {
				collectionElementType = t.getTypeArguments().get( 0 );
			}
		}
		return collectionElementType;
	}
