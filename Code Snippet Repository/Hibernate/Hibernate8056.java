	@Test
	@TestForIssue( jiraKey = "" )
	public void testReferencingSetElements() {
		Triggerable triggerable = logInspection.watchForLogMessages( "HHH90000016" );

		// first the accepted ways
		compileQuery( "select elements(h.nickNames) from Human h" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();
		compileQuery( "select h from Human h where h.name.first in elements(h.nickNames)" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();

		// then the deprecated way
		compileQuery( "select h.nickNames.elements from Human h" );
		assertTrue( triggerable.wasTriggered() );
	}
