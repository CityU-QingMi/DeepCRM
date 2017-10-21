	public String getPropertyName() {
		Elements elementsUtil = parent.getContext().getElementUtils();
		if ( element.getKind() == ElementKind.FIELD ) {
			return element.getSimpleName().toString();
		}
		else if ( element.getKind() == ElementKind.METHOD ) {
			String name = element.getSimpleName().toString();
			if ( name.startsWith( "get" ) ) {
				return elementsUtil.getName( Introspector.decapitalize( name.substring( "get".length() ) ) ).toString();
			}
			else if ( name.startsWith( "is" ) ) {
				return ( elementsUtil.getName( Introspector.decapitalize( name.substring( "is".length() ) ) ) ).toString();
			}
			return elementsUtil.getName( Introspector.decapitalize( name ) ).toString();
		}
		else {
			return elementsUtil.getName( element.getSimpleName() + "/* " + element.getKind() + " */" ).toString();
		}
	}
