	private AST parse(String input, boolean logging) throws RecognitionException, TokenStreamException {
		if ( logging ) {
			System.out.println( "input: ->" + input + "<-" );
		}

		HqlParser parser = HqlParser.getInstance( input );
		parser.setFilter( false );
		parser.statement();
		AST ast = parser.getAST();

		if ( logging ) {
			System.out.println( "AST  :  " + ast.toStringTree() + "" );
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			parser.showAst( ast, new PrintStream( baos ) );
			System.out.println( baos.toString() );
		}

		assertEquals( "At least one error occurred during parsing!", 0, parser.getParseErrorHandler().getErrorCount() );

		return ast;
	}
