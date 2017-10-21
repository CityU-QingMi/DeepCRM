	@Test
	@TestForIssue( jiraKey = "" )
	public void testGetSuperclass() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Reportable reportable = s.get( Reportable.class, blogEntryId );
		assertEquals( "John Doe", reportable.getReportedBy() );
		assertEquals( "detail", ( (BlogEntry) reportable ).getDetail() );
		tx.commit();
		s.close();
	}
