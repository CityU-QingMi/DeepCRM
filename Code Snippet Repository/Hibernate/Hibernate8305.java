	@Test
	@TestForIssue( jiraKey = "" )
	public void selectWhereTypeEqual() {
		final Session s = openSession();
		s.getTransaction().begin();

		s.createQuery( "from Polygon p where type(p) = Quadrilateral" ).list();

		s.getTransaction().commit();
		s.close();
	}
