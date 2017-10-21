	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {

			Product book = new Product();
			book.setId( 1L );
			book.setName( "High-Performance Java Persistence" );
			book.setDescription( "get the most out of your persistence layer" );
			book.setPriceCents( 29_99 );
			book.setQuantity( 10_000 );

			entityManager.persist( book );
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {

			Product book = entityManager.find( Product.class, 1L );
			book.setPriceCents( 24_99 );
		} );
	}
