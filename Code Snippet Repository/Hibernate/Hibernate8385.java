	@Test
	@TestForIssue( jiraKey = "" )
	public void testCriteriaSubclass() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		BlogEntry blogEntry =
				(BlogEntry) s.createCriteria( BlogEntry.class, "r" )
						.add( Restrictions.eq( "r.reportedBy", "John Doe" ) )
						.uniqueResult();
		assertEquals( "John Doe", blogEntry.getReportedBy() );
		assertEquals( "detail", ( blogEntry ).getDetail() );
		tx.commit();
		s.close();
	}
