	@Test
	@SkipForDialect( Oracle8iDialect.class )
	public void testDuplicateImplicitJoinInSelect() {
		// This test causes failures on theta-join dialects because the SQL is different.  The old parser
		// duplicates the condition, whereas the new parser does not.  The queries are semantically the
		// same however.

// the classic translator handles this incorrectly; the explicit join and the implicit ones should create separate physical SQL joins...
//		assertTranslation( "select an.mother.bodyWeight from Animal an join an.mother m where an.mother.bodyWeight > 10" );
		assertTranslation( "select an.mother.bodyWeight from Animal an where an.mother.bodyWeight > 10" );
		//assertTranslation("select an.mother from Animal an where an.mother.bodyWeight is not null");
		assertTranslation( "select an.mother.bodyWeight from Animal an order by an.mother.bodyWeight" );
	}
