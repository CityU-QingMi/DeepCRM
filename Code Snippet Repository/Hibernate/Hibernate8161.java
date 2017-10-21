	@Test
	@TestForIssue( jiraKey = "" )
	public void testSubQueryAsSimpleCaseTestExpression() {
		final String query = "SELECT CASE (SELECT COUNT(r.id) FROM Root r) WHEN  1 THEN 1 ELSE 0 END FROM Leaf l";
		// simple syntax check
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( query ).list();
		s.getTransaction().commit();
		s.close();
	}
