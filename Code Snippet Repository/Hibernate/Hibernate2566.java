	@Override
	protected AST createIntoClause(String path, AST propertySpec) throws SemanticException {
		Queryable persister = (Queryable) getSessionFactoryHelper().requireClassPersister( path );

		IntoClause intoClause = (IntoClause) getASTFactory().create( INTO, persister.getEntityName() );
		intoClause.setFirstChild( propertySpec );
		intoClause.initialize( persister );

		addQuerySpaces( persister.getQuerySpaces() );

		return intoClause;
	}
