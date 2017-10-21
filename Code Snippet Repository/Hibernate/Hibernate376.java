	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			for ( long i = 1; i <= 5; i++ ) {
				if(i % 3 == 0) {
					entityManager.flush();
				}
				Product product = new Product();
				product.setName( String.format( "Product %d", i ) );
				entityManager.persist( product );
			}
		} );
	}
