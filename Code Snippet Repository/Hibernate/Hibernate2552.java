	@Override
	public void expectNamedParameterName() throws TokenStreamException {
		// we expect the token following a COLON (':') to be the name of a named parameter.
		// if the following token is anything other than IDENT we convert its type if possible.

		// NOTE : the LT() call is more expensive than the LA() call; so we
		// use LA() first to see if LT() is needed.
		if ( LA( 1 ) != IDENT ) {
			final HqlToken nextToken = (HqlToken) LT( 1 );
			if ( nextToken.isPossibleID() ) {
				LOG.debugf(
						"Converting keyword [%s] following COLON to IDENT as an expected parameter name",
						nextToken.getText()
				);
				nextToken.setType( IDENT );
			}
		}
	}
