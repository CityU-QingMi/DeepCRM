	@Override
	public void matchOptionalFrom() throws RecognitionException, TokenStreamException {
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST optionalFrom_AST = null;

		if ( LA( 1 ) == FROM ) {
			if ( LA( 2 ) != DOT ) {
				match( FROM );
				optionalFrom_AST = (AST) currentAST.root;
				returnAST = optionalFrom_AST;
			}
		}
	}
