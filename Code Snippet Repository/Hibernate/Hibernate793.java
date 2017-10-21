	public Namespace adjustDefaultNamespace(Identifier catalogName, Identifier schemaName) {
		final Namespace.Name name = new Namespace.Name( catalogName, schemaName );
		if ( implicitNamespace.getName().equals( name ) ) {
			return implicitNamespace;
		}

		Namespace namespace = namespaceMap.get( name );
		if ( namespace == null ) {
			namespace = makeNamespace( name );
		}
		implicitNamespace = namespace;
		return implicitNamespace;
	}
