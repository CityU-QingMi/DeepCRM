	@Test
	public void test() {
		Integer productId = doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::basic-nationalized-persist-example[]
			final Product product = new Product();
			product.setId( 1 );
			product.setName( "Mobile phone" );
			product.setWarranty( "My product warranty" );

			entityManager.persist( product );
			//end::basic-nationalized-persist-example[]

			return product.getId();
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Product product = entityManager.find( Product.class, productId );
			assertEquals( "My product warranty", product.getWarranty() );
		} );
	}
