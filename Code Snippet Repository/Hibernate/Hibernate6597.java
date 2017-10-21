	@Test
	@TestForIssue( jiraKey = "" )
	@FailureExpected( jiraKey = "" )
	public void testUseOfJoinColumnOrFormula() {
		Metadata metadata = new MetadataSources()
				.addAnnotatedClass( A.class )
				.addAnnotatedClass( D.class )
				.buildMetadata();

		// Binding to the mapping model works after the simple change for HHH-9897
		// But building the SessionFactory fails in the collection persister trying to
		// use the formula (it expects Columns too)
		metadata.buildSessionFactory().close();
	}
