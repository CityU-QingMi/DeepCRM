	@Override
	public void weakKeywords() throws TokenStreamException {

		int t = LA( 1 );
		switch ( t ) {
			case ORDER:
			case GROUP:
				// Case 1: Multi token keywords GROUP BY and ORDER BY
				// The next token ( LT(2) ) should be 'by'... otherwise, this is just an ident.
				if ( LA( 2 ) != LITERAL_by ) {
					LT( 1 ).setType( IDENT );
					if ( LOG.isDebugEnabled() ) {
						LOG.debugf( "weakKeywords() : new LT(1) token - %s", LT( 1 ) );
					}
				}
				break;
			default:
				// Case 2: The current token is after FROM and before '.'.
				if ( LA( 0 ) == FROM && t != IDENT && LA( 2 ) == DOT ) {
					HqlToken hqlToken = (HqlToken) LT( 1 );
					if ( hqlToken.isPossibleID() ) {
						hqlToken.setType( IDENT );
						if ( LOG.isDebugEnabled() ) {
							LOG.debugf( "weakKeywords() : new LT(1) token - %s", LT( 1 ) );
						}
					}
				}
				break;
		}
	}
