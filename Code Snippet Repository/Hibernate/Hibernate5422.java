	@Test
	@TestForIssue( jiraKey = "" )
	public void testSubstr() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			String location = (String) session.createQuery(
				"select substr(e.city, 0, 4) " +
				"from Event e " +
				"where e.id = :id")
			.setParameter( "id", event.id )
			.getSingleResult();
			assertEquals( "Cluj", location);
		} );
	}
