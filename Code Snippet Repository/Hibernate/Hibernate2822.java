	private void doPathExpression(String token, QueryTranslatorImpl q) throws QueryException {

		preprocess( token, q );

		StringTokenizer tokens = new StringTokenizer( token, ".", true );
		pathExpressionParser.start( q );
		while ( tokens.hasMoreTokens() ) {
			pathExpressionParser.token( tokens.nextToken(), q );
		}
		pathExpressionParser.end( q );
		if ( pathExpressionParser.isCollectionValued() ) {
			openExpression( q, "" );
			appendToken( q, pathExpressionParser.getCollectionSubquery( q.getEnabledFilters() ) );
			closeExpression( q, "" );
			// this is ugly here, but needed because its a subquery
			q.addQuerySpaces( q.getCollectionPersister( pathExpressionParser.getCollectionRole() ).getCollectionSpaces() );
		}
		else {
			if ( pathExpressionParser.isExpectingCollectionIndex() ) {
				expectingIndex++;
			}
			else {
				addJoin( pathExpressionParser.getWhereJoin(), q );
				appendToken( q, pathExpressionParser.getWhereColumn() );
			}
		}
	}
