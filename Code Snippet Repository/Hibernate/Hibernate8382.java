	@Test
	@TestForIssue( jiraKey = "" )
	public void testQuerySuperclass() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Reportable reportable = (Reportable) s.createQuery(
				"from Reportable where reportedBy='John Doe'"
		).uniqueResult();
		assertEquals( "John Doe", reportable.getReportedBy() );
		assertEquals( "detail", ( (BlogEntry) reportable ).getDetail() );
		tx.commit();
		s.close();
	}
