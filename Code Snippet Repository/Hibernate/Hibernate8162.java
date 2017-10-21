	@Test
	@TestForIssue( jiraKey = "" )
	public void testSubQueryAsSimpleCaseWhenExpression() {
		final String query = "SELECT CASE l.id WHEN (SELECT COUNT(r.id) FROM Root r) THEN 1 ELSE 0 END FROM Leaf l";
		// simple syntax check
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( query ).list();
		s.getTransaction().commit();
		s.close();
	}
