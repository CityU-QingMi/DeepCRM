	private void assertEjbqlEqualsHql(String ejbql, String hql) {
		QueryTranslatorFactory ast = new ASTQueryTranslatorFactory();

		QueryTranslator queryTranslator = ast.createQueryTranslator( hql, hql, Collections.EMPTY_MAP, sessionFactory(), null );
		queryTranslator.compile( Collections.EMPTY_MAP, true );
		String hqlSql = queryTranslator.getSQLString();

		queryTranslator = ast.createQueryTranslator( ejbql, ejbql, Collections.EMPTY_MAP, sessionFactory(), null );
		queryTranslator.compile( Collections.EMPTY_MAP, true );
		String ejbqlSql = queryTranslator.getSQLString();

		assertEquals( hqlSql, ejbqlSql );
	}
