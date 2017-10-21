	@Override
	public void handlePrimaryExpressionDotIdent() throws TokenStreamException {
		if ( LA( 2 ) == DOT && LA( 3 ) != IDENT ) {
			// See if the second lookahead token can be an identifier.
			HqlToken t = (HqlToken) LT( 3 );
			if ( t.isPossibleID() ) {
				// Set it!
				t.setType( IDENT );
				if ( LOG.isDebugEnabled() ) {
					LOG.debugf( "handleDotIdent() : new LT(3) token - %s", LT( 1 ) );
				}
			}
		}
	}
