	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::identifiers-generators-pooled-lo-optimizer-persist-example[]
			for ( long i = 1; i <= 5; i++ ) {
				if(i % 3 == 0) {
					entityManager.flush();
				}
				Product product = new Product();
				product.setName( String.format( "Product %d", i ) );
				product.setNumber( String.format( "P_100_%d", i ) );
				entityManager.persist( product );
			}
			//end::identifiers-generators-pooled-lo-optimizer-persist-example[]
		} );
	}
