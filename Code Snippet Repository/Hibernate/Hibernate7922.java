	@Test
	@TestForIssue( jiraKey = "" )
	public void testQueryMetadataRetrievalWithFetching() {
		// HHH-1464 : there was a problem due to the fact they we polled
		// the shallow version of the query plan to get the metadata.
		Session s = openSession();
		Query query = s.createQuery( "from Animal a inner join fetch a.mother" );
		assertEquals( 1, query.getReturnTypes().length );
		assertNull( query.getReturnAliases() );
		s.close();
	}
