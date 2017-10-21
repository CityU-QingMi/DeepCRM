	@Override
	public void handleDotIdent() throws TokenStreamException {
		// This handles HHH-354, where there is a strange property name in a where clause.
		// If the lookahead contains a DOT then something that isn't an IDENT...
		if ( LA( 1 ) == DOT && LA( 2 ) != IDENT ) {
			// See if the second lookahead token can be an identifier.
			HqlToken t = (HqlToken) LT( 2 );
			if ( t.isPossibleID() ) {
				// Set it!
				LT( 2 ).setType( IDENT );
				if ( LOG.isDebugEnabled() ) {
					LOG.debugf( "handleDotIdent() : new LT(2) token - %s", LT( 1 ) );
				}
			}
		}
	}
