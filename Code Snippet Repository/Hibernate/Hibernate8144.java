	protected Exception compileBadHql(String hql, boolean scalar) {
		QueryTranslator newQueryTranslator;
		Map replacements = null;
		Exception newException = null;
		SessionFactoryImplementor factory = sessionFactory();
		try {
			QueryTranslatorFactory ast = new ASTQueryTranslatorFactory();
			newQueryTranslator = ast.createQueryTranslator( hql, hql, Collections.EMPTY_MAP, factory, null );
			newQueryTranslator.compile( replacements, scalar );
		}
		catch ( QueryException e ) {
			newException = e;
		}
		catch ( MappingException e ) {
			newException = e;
		}
		assertNotNull( "Expected exception from compilation of '" + hql + "'!", newException );
		return newException;
	}
