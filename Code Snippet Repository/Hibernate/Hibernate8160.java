	@Test
	@TestForIssue( jiraKey = "" )
	public void testSubQueryAsCaseElseResultExpression() {
		final String query = "SELECT CASE WHEN  l.id > 1 THEN 1 ELSE (SELECT COUNT(r.id) FROM Root r) END FROM Leaf l";
		// simple syntax check
		Session s = openSession();
		s.beginTransaction();
		s.createQuery( query ).list();
		s.getTransaction().commit();
		s.close();
	}
