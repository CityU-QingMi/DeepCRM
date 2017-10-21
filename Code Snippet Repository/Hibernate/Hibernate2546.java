	@Override
	public AST handleIdentifierError(Token token, RecognitionException ex)
			throws RecognitionException, TokenStreamException {
		// If the token can tell us if it could be an identifier...
		if ( token instanceof HqlToken ) {
			HqlToken hqlToken = (HqlToken) token;
			// ... and the token could be an identifier and the error is
			// a mismatched token error ...
			if ( hqlToken.isPossibleID() && ( ex instanceof MismatchedTokenException ) ) {
				MismatchedTokenException mte = (MismatchedTokenException) ex;
				// ... and the expected token type was an identifier, then:
				if ( mte.expecting == HqlTokenTypes.IDENT ) {
					// Use the token as an identifier.
					reportWarning(
							"Keyword  '"
									+ token.getText()
									+ "' is being interpreted as an identifier due to: " + mte.getMessage()
					);
					// Add the token to the AST.
					ASTPair currentAST = new ASTPair();
					token.setType( HqlTokenTypes.WEIRD_IDENT );
					astFactory.addASTChild( currentAST, astFactory.create( token ) );
					consume();
					return currentAST.root;
				}
			} // if
		} // if
		// Otherwise, handle the error normally.
		return super.handleIdentifierError( token, ex );
	}
