	@Test
	public void testLifecycle() {
		//tag::mapping-Target-persist-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {

			City cluj = new City();
			cluj.setName( "Cluj" );
			cluj.setCoordinates( new GPS( 46.77120, 23.62360 ) );

			entityManager.persist( cluj );
		} );
		//end::mapping-Target-persist-example[]


		//tag::mapping-Target-fetching-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {

			City cluj = entityManager.find( City.class, 1L );

			assertEquals( 46.77120, cluj.getCoordinates().x(), 0.00001 );
			assertEquals( 23.62360, cluj.getCoordinates().y(), 0.00001 );
		} );
		//end::mapping-Target-fetching-example[]
	}
