	@Test
	public void test() {
		//tag::collection-immutability-persist-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {
			Batch batch = new Batch();
			batch.setId( 1L );
			batch.setName( "Change request" );

			Event event1 = new Event();
			event1.setId( 1L );
			event1.setCreatedOn( new Date( ) );
			event1.setMessage( "Update Hibernate User Guide" );

			Event event2 = new Event();
			event2.setId( 2L );
			event2.setCreatedOn( new Date( ) );
			event2.setMessage( "Update Hibernate Getting Started Guide" );

			batch.getEvents().add( event1 );
			batch.getEvents().add( event2 );

			entityManager.persist( batch );
		} );
		//end::collection-immutability-persist-example[]
		//tag::collection-entity-update-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {
			Batch batch = entityManager.find( Batch.class, 1L );
			log.info( "Change batch name" );
			batch.setName( "Proposed change request" );
		} );
		//end::collection-entity-update-example[]
		//tag::collection-immutability-update-example[]
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {
				Batch batch = entityManager.find( Batch.class, 1L );
				batch.getEvents().clear();
			} );
		}
		catch ( Exception e ) {
			log.error( "Immutable collections cannot be modified" );
		}
		//end::collection-immutability-update-example[]
	}
