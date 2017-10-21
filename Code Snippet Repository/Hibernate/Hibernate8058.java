	@Test
	@TestForIssue( jiraKey = "" )
	public void testReferencingListIndices() {
		Triggerable triggerable = logInspection.watchForLogMessages( "HHH90000016" );

		// first the accepted ways
		compileQuery( "select indices(u.permissions) from User u" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();
		compileQuery( "select u from User u where u.userName in indices(u.permissions)" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();

		// then the deprecated way
		compileQuery( "select u.permissions.indices from User u" );
		assertTrue( triggerable.wasTriggered() );
	}
