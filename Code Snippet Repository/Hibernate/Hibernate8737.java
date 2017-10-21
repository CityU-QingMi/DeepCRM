	@Test
	@TestForIssue(jiraKey = "")
	public void testAliasWithLocale() {
		// Without the HHH-8579 fix, this will generate non-ascii query aliases.
		String hql = "from IAmAFoo";
		
		QueryTranslatorFactory ast = new ASTQueryTranslatorFactory();
		QueryTranslator queryTranslator = ast.createQueryTranslator(
				hql, hql, Collections.EMPTY_MAP, sessionFactory(), null );
		queryTranslator.compile( Collections.EMPTY_MAP, false );
		String sql = queryTranslator.getSQLString();
		
		assertTrue( sql.matches( asciiRegex ) );
	}
