	@Override
	public Boolean visitDeclared(DeclaredType declaredType, Element element) {
		if ( ElementKind.ENUM.equals( element.getKind() ) ) {
			return Boolean.TRUE;
		}

		if ( ElementKind.CLASS.equals( element.getKind() ) || ElementKind.INTERFACE.equals( element.getKind() ) ) {
			TypeElement typeElement = ( (TypeElement) element );
			String typeName = typeElement.getQualifiedName().toString();
			if ( Constants.BASIC_TYPES.contains( typeName ) ) {
				return Boolean.TRUE;
			}
			if ( TypeUtils.containsAnnotation( element, Constants.EMBEDDABLE ) ) {
				return Boolean.TRUE;
			}
			for ( TypeMirror mirror : typeElement.getInterfaces() ) {
				TypeElement interfaceElement = (TypeElement) context.getTypeUtils().asElement( mirror );
				if ( "java.io.Serializable".equals( interfaceElement.getQualifiedName().toString() ) ) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}
