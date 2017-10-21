	public void prepare() throws SemanticException {
		constructorArgumentTypes = resolveConstructorArgumentTypes();
		String path = ( (PathNode) getFirstChild() ).getPath();
		if ( "map".equals( path.toLowerCase(Locale.ROOT) ) ) {
			isMap = true;
			resultType = Map.class;
		}
		else if ( "list".equals( path.toLowerCase(Locale.ROOT) ) ) {
			isList = true;
			resultType = List.class;
		}
		else {
			constructor = resolveConstructor( path );
			resultType = constructor.getDeclaringClass();
		}
	}
