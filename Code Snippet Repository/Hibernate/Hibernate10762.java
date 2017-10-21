	@Test
	public void testRevisionHistory() {
		for ( Integer revision : Arrays.asList( 1, 2, 3, 4, 5, 6 ) ) {
			final Project project = getAuditReader().find( Project.class, 1, revision );
			if ( revision == 1 ) {
				assertEquals( new Project( 1, "fooName" ), project );
			}
			else {
				assertEquals( new Project( 1, "fooName" + revision ), project );
			}
		}
	}
