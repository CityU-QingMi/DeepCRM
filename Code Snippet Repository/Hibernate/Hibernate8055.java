	@Test
	@TestForIssue( jiraKey = "" )
	public void testReferencingBagElements() {
		Triggerable triggerable = logInspection.watchForLogMessages( "HHH90000016" );

		// first the accepted ways
		compileQuery( "select elements(h.friends) from Human h" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();
		compileQuery( "select h from Human h where h in elements(h.friends)" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();

		// then the deprecated way
		compileQuery( "select h.friends.elements from Human h" );
		assertTrue( triggerable.wasTriggered() );
	}
