	@Test
	public void testGroupByFunction() {
		if ( getDialect() instanceof Oracle8iDialect ) return; // the new hiearchy...
		if ( getDialect() instanceof PostgreSQLDialect || getDialect() instanceof PostgreSQL81Dialect ) return;
		if ( getDialect() instanceof TeradataDialect) return;
		if ( ! H2Dialect.class.isInstance( getDialect() ) ) {
			// H2 has no year function
			assertTranslation( "select count(*) from Human h group by year(h.birthdate)" );
			assertTranslation( "select count(*) from Human h group by year(sysdate)" );
		}
		assertTranslation( "select count(*) from Human h group by trunc( sqrt(h.bodyWeight*4)/2 )" );
	}
