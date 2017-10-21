	@Test
	@TestForIssue( jiraKey = "" )
	public void testReferencingSize() {
		Triggerable triggerable = logInspection.watchForLogMessages( "HHH90000016" );

		// first the accepted ways
		compileQuery( "select size(h.family) from Human h" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();
		compileQuery( "select h from Human h where size(h.family) = 1" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();

		// then the deprecated way
		compileQuery( "select h.family.size from Human h" );
		assertTrue( triggerable.wasTriggered() );
	}
