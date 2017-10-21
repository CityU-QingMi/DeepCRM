	@Test
	public void testWhere() throws Exception {
		assertTranslation( "from Animal an where an.bodyWeight > 10" );
		// 2004-06-26 [jsd] This one requires NOT GT => LE transform.
		assertTranslation( "from Animal an where not an.bodyWeight > 10" );
		assertTranslation( "from Animal an where an.bodyWeight between 0 and 10" );
		assertTranslation( "from Animal an where an.bodyWeight not between 0 and 10" );
		assertTranslation( "from Animal an where sqrt(an.bodyWeight)/2 > 10" );
		// 2004-06-27 [jsd] Recognize 'is null' properly.  Generate 'and' and 'or' as well.
		assertTranslation( "from Animal an where (an.bodyWeight > 10 and an.bodyWeight < 100) or an.bodyWeight is null" );
	}
