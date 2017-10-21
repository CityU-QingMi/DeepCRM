	@Test
	@TestForIssue( jiraKey = "" )
	public void testSubstring() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			String location = (String) session.createQuery(
				"select substring(e.city, 0, 5) " +
				"from Event e " +
				"where e.id = :id")
			.setParameter( "id", event.id )
			.getSingleResult();
			assertEquals( "Cluj", location);
		} );
	}
