	@Test
	public void testImplicitJoins() throws Exception {
		// Two dots...
		assertTranslation( "from Animal an where an.mother.bodyWeight > ?" );
		assertTranslation( "from Animal an where an.mother.bodyWeight > 10" );
		assertTranslation( "from Dog dog where dog.mother.bodyWeight > 10" );
		// Three dots...
		assertTranslation( "from Animal an where an.mother.mother.bodyWeight > 10" );
		// The new QT doesn't throw an exception here, so this belongs in ASTQueryTranslator test. [jsd]
//		assertTranslation( "from Animal an where an.offspring.mother.bodyWeight > 10" );
		// Is not null (unary postfix operator)
		assertTranslation( "from Animal an where an.mother is not null" );
		// ID property shortut (no implicit join)
		assertTranslation( "from Animal an where an.mother.id = 123" );
	}
