	@Test
	@TestForIssue( jiraKey = "" )
	public void testReferencingMapIndices() {
		// NOTE : JPA's KEY ought to work fine as we never supported
		// 		that in the legacy form...

		Triggerable triggerable = logInspection.watchForLogMessages( "HHH90000016" );

		// first the accepted ways
		compileQuery( "select indices(h.family) from Human h" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();
		compileQuery( "select h from Human h where h.name.first in indices(h.family)" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();

		// then the deprecated way
		compileQuery( "select h.family.indices from Human h" );
		assertTrue( triggerable.wasTriggered() );
	}
