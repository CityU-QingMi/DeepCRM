	@Test
	@TestForIssue( jiraKey = "" )
	public void testExpandListParameter() {
		final Object[] namesArray = new Object[] {
				"ZOO 1", "ZOO 2", "ZOO 3", "ZOO 4", "ZOO 5", "ZOO 6", "ZOO 7",
				"ZOO 8", "ZOO 9", "ZOO 10", "ZOO 11", "ZOO 12"
		};
		final Object[] citiesArray = new Object[] {
				"City 1", "City 2", "City 3", "City 4", "City 5", "City 6", "City 7",
				"City 8", "City 9", "City 10", "City 11", "City 12"
		};

		Session session = openSession();

		session.getTransaction().begin();
		Address address = new Address();
		Zoo zoo = new Zoo( "ZOO 1", address );
		address.setCity( "City 1" );
		session.save( zoo );
		session.getTransaction().commit();

		session.clear();

		session.getTransaction().begin();
		List result = session.createQuery( "FROM Zoo z WHERE z.name IN (?1) and z.address.city IN (?11)" )
				.setParameterList( "1", namesArray )
				.setParameterList( "11", citiesArray )
				.list();
		assertEquals( 1, result.size() );
		session.getTransaction().commit();

		session.clear();

		session.getTransaction().begin();
		zoo = (Zoo) session.get( Zoo.class, zoo.getId() );
		session.delete( zoo );
		session.getTransaction().commit();

		session.close();
	}
