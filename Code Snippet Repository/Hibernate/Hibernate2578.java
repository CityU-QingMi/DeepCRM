	private HqlParser parse(boolean filter) throws TokenStreamException {
		// Parse the query string into an HQL AST.
		final HqlParser parser = HqlParser.getInstance( hql );
		parser.setFilter( filter );

		LOG.debugf( "parse() - HQL: %s", hql );
		try {
			parser.statement();
		}
		catch (RecognitionException e) {
			throw new HibernateException( "Unexpected error parsing HQL", e );
		}

		final AST hqlAst = parser.getAST();
		parser.getParseErrorHandler().throwQueryException();

		final NodeTraverser walker = new NodeTraverser( new JavaConstantConverter( factory ) );
		walker.traverseDepthFirst( hqlAst );

		showHqlAst( hqlAst );

		return parser;
	}
