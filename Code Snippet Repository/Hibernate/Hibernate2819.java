	public void end(QueryTranslatorImpl q) throws QueryException {
		if ( expectingPathContinuation ) {
			expectingPathContinuation = false;
			PathExpressionParser.CollectionElement element = pathExpressionParser.lastCollectionElement();
			if ( element.elementColumns.length != 1 ) {
				throw new QueryException( "path expression ended in composite collection element" );
			}
			appendToken( q, element.elementColumns[0] );
			addToCurrentJoin( element );
		}
		token( ")", q );
	}
