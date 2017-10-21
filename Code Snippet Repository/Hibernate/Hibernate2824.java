	private boolean continuePathExpression(String token, QueryTranslatorImpl q) throws QueryException {

		expectingPathContinuation = false;

		PathExpressionParser.CollectionElement element = pathExpressionParser.lastCollectionElement();

		if ( token.startsWith( "." ) ) { // the path expression continues after a ]

			doPathExpression( getElementName( element, q ) + token, q ); // careful with this!

			addToCurrentJoin( element );
			return true; //NOTE: EARLY EXIT!

		}

		else { // the path expression ends at the ]
			if ( element.elementColumns.length != 1 ) {
				throw new QueryException( "path expression ended in composite collection element" );
			}
			appendToken( q, element.elementColumns[0] );
			addToCurrentJoin( element );
			return false;
		}
	}
