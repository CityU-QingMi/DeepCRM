	public Namespace locateNamespace(Identifier catalogName, Identifier schemaName) {
		if ( catalogName == null && schemaName == null ) {
			return getDefaultNamespace();
		}

		final Namespace.Name name = new Namespace.Name( catalogName, schemaName );
		Namespace namespace = namespaceMap.get( name );
		if ( namespace == null ) {
			namespace = makeNamespace( name );
		}
		return namespace;
	}
