	@Test
	public void test() {
		Integer productId = doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );

			//tag::basic-nclob-persist-example[]
			String warranty = "My product warranty";

			final Product product = new Product();
			product.setId( 1 );
			product.setName( "Mobile phone" );

			session.doWork( connection -> {
				product.setWarranty( connection.createNClob() );
				product.getWarranty().setString( 1, warranty );
			} );

			entityManager.persist( product );
			//end::basic-nclob-persist-example[]

			return product.getId();
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			try {
				//tag::basic-nclob-find-example[]

				Product product = entityManager.find( Product.class, productId );
				try (Reader reader = product.getWarranty().getCharacterStream()) {
					assertEquals( "My product warranty", toString( reader ) );
				}
				//end::basic-nclob-find-example[]
			}
			catch (Exception e) {
				fail( e.getMessage() );
			}
		} );
	}
