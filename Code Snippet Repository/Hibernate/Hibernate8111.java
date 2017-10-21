	@Test
	public void testClassName() throws Exception {
		// The Zoo reference is OK; Zoo is discriminator-based;
		// the old parser could handle these correctly
		//
		// However, the Animal one ares not; Animal is joined subclassing;
		// the old parser does not handle thee correctly.  The new parser
		// previously did not handle them correctly in that same way.  So they
		// used to pass regression even though the output was bogus SQL...
		//
		// I have moved the Animal ones (plus duplicating the Zoo one)
		// to ASTParserLoadingTest for syntax checking.
		assertTranslation( "from Zoo zoo where zoo.class = PettingZoo" );
//		assertTranslation( "from DomesticAnimal an where an.class = Dog" );
//		assertTranslation( "from Animal an where an.class = Dog" );
	}
