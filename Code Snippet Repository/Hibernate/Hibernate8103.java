	@SuppressWarnings( {""})
	@Test
	public void testExpressionInFunction() throws Exception {
		assertTranslation( "from Animal an where an.bodyWeight > abs(3-5)" );
		assertTranslation( "from Animal an where an.bodyWeight > abs(3/5)" );
		assertTranslation( "from Animal an where an.bodyWeight > abs(3+5)" );
		assertTranslation( "from Animal an where an.bodyWeight > abs(3*5)" );
		SQLFunction concat = sessionFactory().getSqlFunctionRegistry().findSQLFunction( "concat");
		List list = new ArrayList();
		list.add("'fat'");
		list.add("'skinny'");
		assertTranslation(
				"from Animal an where an.description = " +
						concat.render( StringType.INSTANCE, list, sessionFactory() )
		);
	}
