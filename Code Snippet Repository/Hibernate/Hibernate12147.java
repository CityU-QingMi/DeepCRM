		@Override
		public Boolean visitDeclared(DeclaredType declaredType, Element element) {
			TypeElement returnedElement = (TypeElement) context.getTypeUtils().asElement( declaredType );

			String fqNameOfReturnType = returnedElement.getQualifiedName().toString();
			String collection = Constants.COLLECTIONS.get( fqNameOfReturnType );
			if ( collection != null ) {
				TypeMirror collectionElementType = TypeUtils.getCollectionElementType(
						declaredType, fqNameOfReturnType, null, context
				);
				returnedElement = (TypeElement) context.getTypeUtils().asElement( collectionElementType );
			}

			if ( type.getQualifiedName().toString().equals( returnedElement.getQualifiedName().toString() ) ) {
				return Boolean.TRUE;
			}
			else {
				return Boolean.FALSE;
			}
		}
