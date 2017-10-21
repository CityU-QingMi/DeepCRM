	@Test
	@TestForIssue( jiraKey = "" )
	public void testCoalesceAndNvl() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			String location = (String) session.createQuery(
				"select coalesce(e.district, 'N/A') " +
				"from Event e " +
				"where e.id = :id")
			.setParameter( "id", event.id )
			.getSingleResult();
			assertEquals( "N/A", location);

			location = (String) session.createQuery(
				"select nvl(e.district, 'N/A') " +
				"from Event e " +
				"where e.id = :id")
			.setParameter( "id", event.id )
			.getSingleResult();
			assertEquals( "N/A", location);
		} );
	}
