	@Test
	@TestForIssue( jiraKey = "" )
	public void testCriteriaSuperclass() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Reportable reportable =
				(Reportable) s.createCriteria( Reportable.class, "r" )
						.add( Restrictions.eq( "r.reportedBy", "John Doe" ) )
						.uniqueResult();
		assertEquals( "John Doe", reportable.getReportedBy() );
		assertEquals( "detail", ( (BlogEntry) reportable ).getDetail() );
		tx.commit();
		s.close();
	}
