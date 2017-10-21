	@Test
	@TestForIssue( jiraKey = "" )
	public void testQuerySubclass() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		BlogEntry blogEntry = (BlogEntry) s.createQuery(
				"from BlogEntry where reportedBy='John Doe'"
		).uniqueResult();
		assertEquals( "John Doe", blogEntry.getReportedBy() );
		assertEquals( "detail", ( blogEntry ).getDetail() );
		tx.commit();
		s.close();
	}
