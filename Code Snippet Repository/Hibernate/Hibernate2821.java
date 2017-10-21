	private void preprocess(String token, QueryTranslatorImpl q) throws QueryException {
		// ugly hack for cases like "elements(foo.bar.collection)"
		// (multi-part path expression ending in elements or indices)
		String[] tokens = StringHelper.split( ".", token, true );
		if (
				tokens.length > 5 &&
				( CollectionPropertyNames.COLLECTION_ELEMENTS.equals( tokens[tokens.length - 1] )
				|| CollectionPropertyNames.COLLECTION_INDICES.equals( tokens[tokens.length - 1] ) )
		) {
			pathExpressionParser.start( q );
			for ( int i = 0; i < tokens.length - 3; i++ ) {
				pathExpressionParser.token( tokens[i], q );
			}
			pathExpressionParser.token( null, q );
			pathExpressionParser.end( q );
			addJoin( pathExpressionParser.getWhereJoin(), q );
			pathExpressionParser.ignoreInitialJoin();
		}
	}
