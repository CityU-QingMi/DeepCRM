	@Test
	public void test() {
		//tag::entity-immutability-persist-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {
			Event event = new Event();
			event.setId( 1L );
			event.setCreatedOn( new Date( ) );
			event.setMessage( "Hibernate User Guide rocks!" );

			entityManager.persist( event );
		} );
		//end::entity-immutability-persist-example[]
		//tag::entity-immutability-update-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {
			Event event = entityManager.find( Event.class, 1L );
			log.info( "Change event message" );
			event.setMessage( "Hibernate User Guide" );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Event event = entityManager.find( Event.class, 1L );
			assertEquals("Hibernate User Guide rocks!", event.getMessage());
		} );
		//end::entity-immutability-update-example[]
	}
