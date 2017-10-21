	@Test
	@TestForIssue( jiraKey = "" )
	public void testSubQueryAsSearchedCaseExpression() {
		final String query = "SELECT CASE WHEN (SELECT COUNT(r.id) FROM Root r) > 1 THEN 1 ELSE 0 END FROM Leaf l";
		// simple syntax check
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( query ).list();
		s.getTransaction().commit();
		s.close();
	}
