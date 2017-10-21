	@Test
	@TestForIssue(jiraKey = "")
	public void testDatepartFunction() {
		final Session s = openSession();
		s.getTransaction().begin();
		Query query = session.createQuery( "select datepart(month, p.date) from Product p" );
		assertEquals(calendar.get(MONTH) + 1, ((Number) query.uniqueResult()).intValue());
		s.getTransaction().commit();
		s.close();
	}
