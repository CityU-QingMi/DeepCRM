	@Test
	public void testEjb3PositionalParameters() throws Exception {
		QueryTranslatorImpl qt = compile( "from Animal a where a.bodyWeight = ?1" );
		AST ast = ( AST ) qt.getSqlAST();

		// make certain that the ejb3-positional param got recognized as a named param
		List namedParams = ASTUtil.collectChildren(
		        ast,
		        new ASTUtil.FilterPredicate() {
			        public boolean exclude(AST n) {
				        return n.getType() != HqlSqlTokenTypes.NAMED_PARAM;
			        }
		        }
		);
		assertTrue( "ejb3 positional param not recognized as a named param", namedParams.size() > 0 );
	}
