	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		this.productId = TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			Product product = new Product( 1 , "Test" );
			product.getItems().add( new Item( "bread", null ) );
			entityManager.persist( product );
			return product.getId();
		} );

		// Revision 2
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			Product product = entityManager.find( Product.class, productId );
			product.getItems().add( new Item( "bread2", 2 ) );
			entityManager.merge( product );
		} );

		// Revision 3
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			Product product = entityManager.find( Product.class, productId );
			product.getItems().remove( 0 );
			entityManager.merge( product );
		} );
	}
