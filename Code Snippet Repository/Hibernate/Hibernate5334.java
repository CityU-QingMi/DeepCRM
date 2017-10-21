	@Test
	@TestForIssue( jiraKey = "")
	public void testUpperCaseWithHyphens() {
		try {
			AccessType.fromExternalName( "READ-ONLY" );
			fail( "should have failed because upper-case using hyphans is not supported." );
		}
		catch (UnknownAccessTypeException ex) {
			// expected
		}
		try {
			AccessType.fromExternalName( "READ-WRITE" );
			fail( "should have failed because upper-case using hyphans is not supported." );
		}
		catch (UnknownAccessTypeException ex) {
			// expected
		}
		try {
			AccessType.fromExternalName( "NONSTRICT-READ-WRITE" );
			fail( "should have failed because upper-case using hyphans is not supported." );
		}
		catch (UnknownAccessTypeException ex) {
			// expected
		}
	}
