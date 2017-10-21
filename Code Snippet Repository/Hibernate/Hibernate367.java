	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Product product = new Product();
			product.setId( 1L );
			product.setName( "Mobile phone" );
			product.setNumber( "123-456-7890" );
			entityManager.persist( product );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::identifiers-rowid-example[]
			Product product = entityManager.find( Product.class, 1L );

			product.setName( "Smart phone" );
			//end::identifiers-rowid-example[]
		} );
	}
