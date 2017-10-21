	private HqlSqlWalker analyze(HqlParser parser, String collectionRole) throws QueryException, RecognitionException {
		final HqlSqlWalker w = new HqlSqlWalker( this, factory, parser, tokenReplacements, collectionRole );
		final AST hqlAst = parser.getAST();

		// Transform the tree.
		w.statement( hqlAst );

		if ( LOG.isDebugEnabled() ) {
			LOG.debug( SQL_TOKEN_PRINTER.showAsString( w.getAST(), "--- SQL AST ---" ) );
		}

		w.getParseErrorHandler().throwQueryException();

		return w;
	}
