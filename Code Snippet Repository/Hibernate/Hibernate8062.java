	private QueryTranslatorImpl compileQuery(String hql) {
		QueryTranslatorFactory ast = new ASTQueryTranslatorFactory();
		QueryTranslatorImpl newQueryTranslator = (QueryTranslatorImpl) ast.createQueryTranslator(
				hql,
				hql,
				Collections.EMPTY_MAP,
				sessionFactory(),
				null
		);
		newQueryTranslator.compile( Collections.emptyMap(), false );
		return newQueryTranslator;
	}
