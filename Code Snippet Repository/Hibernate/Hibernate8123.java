	private AST doParse(String input, boolean filter) throws RecognitionException, TokenStreamException {
		System.out.println( "input: ->" + ASTPrinter.escapeMultibyteChars(input) + "<-" );
		HqlParser parser = HqlParser.getInstance( input );
		parser.setFilter( filter );
		parser.statement();
		AST ast = parser.getAST();
		System.out.println( "AST  :  " + ast.toStringTree() + "" );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		parser.showAst( ast, new PrintStream( baos ) );
		System.out.println( baos.toString() );
		assertEquals( "At least one error occurred during parsing!", 0, parser.getParseErrorHandler().getErrorCount() );
		return ast;
	}
