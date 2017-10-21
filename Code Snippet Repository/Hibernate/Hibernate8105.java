	@Test
	public void testOrderBy() throws Exception {
		assertTranslation( "from Animal an order by an.bodyWeight" );
		assertTranslation( "from Animal an order by an.bodyWeight asc" );
		assertTranslation( "from Animal an order by an.bodyWeight desc" );
		assertTranslation( "from Animal an order by sqrt(an.bodyWeight*4)/2" );
		assertTranslation( "from Animal an order by an.mother.bodyWeight" );
		assertTranslation( "from Animal an order by an.bodyWeight, an.description" );
		assertTranslation( "from Animal an order by an.bodyWeight asc, an.description desc" );
		if ( getDialect() instanceof HSQLDialect || getDialect() instanceof DB2Dialect ) {
			assertTranslation( "from Human h order by sqrt(h.bodyWeight), year(h.birthdate)" );
		}
	}
