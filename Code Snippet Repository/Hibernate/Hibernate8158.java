	@Test
	@TestForIssue( jiraKey = "" )
	public void testSubQueryAsSearchedCaseResultExpression() {
		final String query = "SELECT CASE WHEN l.id IS NOT NULL THEN (SELECT COUNT(r.id) FROM Root r) ELSE 0 END FROM Leaf l";
		// simple syntax check
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( query ).list();
		s.getTransaction().commit();
		s.close();
	}
