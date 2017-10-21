	@Test
	@TestForIssue( jiraKey = "" )
	public void testReferencingListElements() {
		Triggerable triggerable = logInspection.watchForLogMessages( "HHH90000016" );

		// first the accepted ways
		compileQuery( "select elements(u.permissions) from User u" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();
		compileQuery( "select u from User u where u.userName in elements(u.permissions)" );
		assertFalse( triggerable.wasTriggered() );
		triggerable.reset();

		// then the deprecated way
		compileQuery( "select u.permissions.elements from User u" );
		assertTrue( triggerable.wasTriggered() );
	}
