	@Test
	@TestForIssue(jiraKey = "")
	public void testComponentJoinsHHH7849() {
		// Just checking proper query construction and syntax checking via database query parser...
		Session session = openSession();
		session.beginTransaction();
		// use it in WHERE
		session.createQuery( "select c from Component c join c.emb as e where e.stuffs is empty " ).list();

		session.getTransaction().commit();
		session.close();
	}
