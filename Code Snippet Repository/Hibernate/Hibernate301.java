	@Test
	public void test() {
		Integer productId = doInJPA( this::entityManagerFactory, entityManager -> {
			Session session = entityManager.unwrap( Session.class );

			//tag::basic-blob-persist-example[]
			byte[] image = new byte[] {1, 2, 3};

			final Product product = new Product();
			product.setId( 1 );
			product.setName( "Mobile phone" );

			session.doWork( connection -> {
				product.setImage( BlobProxy.generateProxy( image ) );
			} );

			entityManager.persist( product );
			//end::basic-blob-persist-example[]

			return product.getId();
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			try {
				//tag::basic-blob-find-example[]

				Product product = entityManager.find( Product.class, productId );
				
				try (InputStream inputStream = product.getImage().getBinaryStream()) {
					assertArrayEquals(new byte[] {1, 2, 3}, toBytes( inputStream ) );
				}
				//end::basic-blob-find-example[]
			}
			catch (Exception e) {
				fail( e.getMessage() );
			}
		} );
	}
