	@Test
	public void refreshTest() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			City city = new City();
			city.setId( 100L );
			city.setName( "Cluj-Napoca" );
			entityManager.unwrap(Session.class).replicate( city, ReplicationMode.OVERWRITE );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			City city = entityManager.find( City.class, 100L);
			assertEquals("Cluj-Napoca", city.getName());
		} );
	}
