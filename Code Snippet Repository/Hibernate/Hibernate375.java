	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::identifiers-generators-table-persist-example[]
			for ( long i = 1; i <= 3; i++ ) {
				Product product = new Product();
				product.setName( String.format( "Product %d", i ) );
				entityManager.persist( product );
			}
			//end::identifiers-generators-table-persist-example[]
		} );
	}
