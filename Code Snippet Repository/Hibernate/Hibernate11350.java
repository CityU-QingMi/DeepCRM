	@Test
	public void test() {
		connectionProvider.clear();
		doInHibernate( this::sessionFactory, session -> {
			City city = new City();
			city.setId( 1L );
			city.setName( "Cluj-Napoca" );
			session.persist( city );

			assertTrue( connectionProvider.getAcquiredConnections().isEmpty() );
			assertTrue( connectionProvider.getReleasedConnections().isEmpty() );
		} );
		verifyConnections();

		connectionProvider.clear();
		doInHibernate( this::sessionFactory, session -> {
			City city = session.find( City.class, 1L );
			assertEquals( "Cluj-Napoca", city.getName() );
		} );
		verifyConnections();
	}
