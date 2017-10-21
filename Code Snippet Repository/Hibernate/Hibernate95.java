	@Test
	public void testExclude() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::events-exclude-default-listener-persist-example[]
			Publisher publisher = new Publisher();
			publisher.setId( 1L );
			publisher.setName( "Amazon" );

			entityManager.persist( publisher );
			//end::events-exclude-default-listener-persist-example[]
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Publisher publisher = entityManager.find( Publisher.class, 1L );
			assertNull(publisher.getCreatedOn());
		} );
	}
