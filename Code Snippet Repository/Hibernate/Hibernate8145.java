	private void checkSql(QueryTranslator oldQueryTranslator, QueryTranslator newQueryTranslator, String hql, boolean scalar, String sql) {

		String oldsql = oldQueryTranslator.getSQLString();
		String newsql = newQueryTranslator.getSQLString();
		System.out.println( "HQL    : " + ASTPrinter.escapeMultibyteChars(hql) );
		System.out.println( "OLD SQL: " + ASTPrinter.escapeMultibyteChars(oldsql) );
		System.out.println( "NEW SQL: " + ASTPrinter.escapeMultibyteChars(newsql) );
		if ( sql == null ) {
			// Check the generated SQL.                                          ASTPrinter.escapeMultibyteChars(
			assertSQLEquals( "SQL is not the same as the old SQL (scalar=" + scalar + ")", oldsql, newsql );
		}
		else {
			assertSQLEquals( "SQL is not the same as the expected SQL (scalar=" + scalar + ")", sql, newsql );
		}
	}
