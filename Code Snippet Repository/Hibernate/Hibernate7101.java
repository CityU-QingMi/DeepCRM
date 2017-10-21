	@Test
	public void testSimpleTree() throws Exception {
		String input = "select foo from foo in class org.hibernate.test.Foo, fee in class org.hibernate.test.Fee where foo.dependent = fee order by foo.string desc, foo.component.count asc, fee.id";
		HqlParser parser = HqlParser.getInstance( input );
		parser.statement();
		AST ast = parser.getAST();
		ASTPrinter printer = new ASTPrinter( HqlTokenTypes.class );
		printer.showAst( ast, new PrintWriter( System.out ) );
		ASTIterator iterator = new ASTIterator( ast );
		int count = 0;
		while ( iterator.hasNext() ) {
			assertTrue( iterator.next() instanceof AST );
			count++;
		}
		assertEquals( 43, count );

		UnsupportedOperationException uoe = null;
		try {
			iterator.remove();
		}
		catch ( UnsupportedOperationException e ) {
			uoe = e;
		}
		assertNotNull( uoe );
	}
