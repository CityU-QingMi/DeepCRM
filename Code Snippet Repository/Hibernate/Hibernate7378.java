	@Test
	@TestForIssue( jiraKey = "" )
	public void testComponentStateChangeAndDirtiness() {
		Session s = openSession();
		s.beginTransaction();
		User u = new User( "steve", "hibernater", new Person( "Steve Ebersole", new Date(), "Main St") );
		s.persist( u );
		s.flush();
		long intialUpdateCount = sessionFactory().getStatistics().getEntityUpdateCount();
		u.getPerson().setAddress( "Austin" );
		s.flush();
		assertEquals( intialUpdateCount + 1, sessionFactory().getStatistics().getEntityUpdateCount() );
		intialUpdateCount = sessionFactory().getStatistics().getEntityUpdateCount();
		u.getPerson().setAddress( "Cedar Park" );
		s.flush();
		assertEquals( intialUpdateCount + 1, sessionFactory().getStatistics().getEntityUpdateCount() );
		s.delete( u );
		s.getTransaction().commit();
		s.close();
	}
