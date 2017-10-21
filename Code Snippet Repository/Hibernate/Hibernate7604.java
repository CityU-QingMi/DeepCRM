	@Test
	@TestForIssue(jiraKey = "")
	public void testAtn2Function() {
		final Session s = openSession();
		s.getTransaction().begin();
		Query query = session.createQuery("select atn2(p.price, .48) from Product p");
		assertEquals(0.805803, ((Number) query.uniqueResult()).doubleValue(), 0.000001 );
		s.getTransaction().commit();
		s.close();
	}
