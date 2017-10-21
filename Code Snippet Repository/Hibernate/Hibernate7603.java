	@Test
	@TestForIssue(jiraKey = "")
	public void testDatediffFunction() {
		final Session s = openSession();
		s.getTransaction().begin();
		Query query = session.createQuery( "SELECT DATEDIFF( DAY, '1999/07/19 00:00', '1999/07/23 23:59' ) from Product" );
		assertEquals(4, ((Number) query.uniqueResult()).intValue());
		s.getTransaction().commit();
		s.close();
	}
