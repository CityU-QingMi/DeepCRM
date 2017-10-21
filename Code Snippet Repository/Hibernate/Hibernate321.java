	@Test
	public void testLifecycle() {
		//tag::mapping-Parent-persist-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {

			City cluj = new City();
			cluj.setName( "Cluj" );
			cluj.setCoordinates( new GPS( 46.77120, 23.62360 ) );

			entityManager.persist( cluj );
		} );
		//end::mapping-Parent-persist-example[]


		//tag::mapping-Parent-fetching-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {

			City cluj = entityManager.find( City.class, 1L );

			assertSame( cluj, cluj.getCoordinates().getCity() );
		} );
		//end::mapping-Parent-fetching-example[]
	}
