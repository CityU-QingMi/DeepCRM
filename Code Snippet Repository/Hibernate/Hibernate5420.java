	@Test
	@TestForIssue( jiraKey = "" )
	public void testConcat() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			String location = (String) session.createQuery(
				"select concat(e.country, ' - ',  e.city) " +
				"from Event e " +
				"where e.id = :id")
			.setParameter( "id", event.id )
			.getSingleResult();
			assertEquals( "Romania - Cluj-Napoca", location);
		} );
	}
