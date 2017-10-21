	@Override
	protected void afterEntityManagerFactoryBuilt() {
		super.afterEntityManagerFactoryBuilt();
		doInJPA( this::entityManagerFactory, entityManager -> {
			Event event = new Event();
			event.setId( 1L );
			event.setMessage( "ABC" );
			event.setCreatedOn( Timestamp.valueOf( "9999-12-31 00:00:00" ) );
			entityManager.persist( event );
		} );
	}
