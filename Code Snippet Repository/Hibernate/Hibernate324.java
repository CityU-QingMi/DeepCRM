	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::basic-quoting-persistence-example[]
			Product product = new Product();
			product.setId( 1L );
			product.setName( "Mobile phone" );
			product.setNumber( "123-456-7890" );
			entityManager.persist( product );
			//end::basic-quoting-persistence-example[]
		} );
	}
