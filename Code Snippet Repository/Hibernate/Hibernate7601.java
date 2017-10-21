	@Test
	@TestForIssue(jiraKey = "")
	public void testDateaddFunction() {
		final Session s = openSession();
		s.getTransaction().begin();
		Query query = session.createQuery( "select dateadd(dd, 1, p.date) from Product p" );
		assertTrue(calendar.getTime().before((Date) query.uniqueResult()));
		s.getTransaction().commit();
		s.close();
	}
