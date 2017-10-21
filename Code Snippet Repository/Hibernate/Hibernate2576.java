	private void generate(AST sqlAst) throws QueryException, RecognitionException {
		if ( sql == null ) {
			final SqlGenerator gen = new SqlGenerator( factory );
			gen.statement( sqlAst );
			sql = gen.getSQL();
			if ( LOG.isDebugEnabled() ) {
				LOG.debugf( "HQL: %s", hql );
				LOG.debugf( "SQL: %s", sql );
			}
			gen.getParseErrorHandler().throwQueryException();
			collectedParameterSpecifications = gen.getCollectedParameters();
		}
	}
