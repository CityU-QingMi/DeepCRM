	@Test
	@TestForIssue( jiraKey = "" )
	public void testReferencingMapElements() {
		// NOTE : JPA's VALUE ought to work fine as we never supported
		// 		that in the legacy form...

		Triggerable triggerable = logInspection.watchForLogMessages( "HHH90000016" );

		// first the accepted ways
		compileQuery( "select elements(h.family) from Human h" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();
		compileQuery( "select h from Human h where h.name.first in elements(h.family)" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();

		// then the deprecated way
		compileQuery( "select h.family.elements from Human h" );
		assertTrue( triggerable.wasTriggered() );
	}
