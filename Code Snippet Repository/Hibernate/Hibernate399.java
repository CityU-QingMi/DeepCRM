	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {

			//tag::pc-managed-state-update-persist-example[]
			Product book = new Product();
			book.setId( 1L );
			book.setName( "High-Performance Java Persistence" );
			book.setDescription( "Get the most out of your persistence layer" );
			book.setPriceCents( 29_99 );
			book.setQuantity( 10_000 );

			entityManager.persist( book );
			//end::pc-managed-state-update-persist-example[]
		} );


		//tag::pc-managed-state-update-example[]
		doInJPA( this::entityManagerFactory, entityManager -> {
			Product book = entityManager.find( Product.class, 1L );
			book.setPriceCents( 24_99 );
		} );
		//end::pc-managed-state-update-example[]
	}
