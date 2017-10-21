	@Test
	@Priority(10)
	public void initData() {
		// Revision 1
		headerId = TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			Header h1 = new Header( "h1" );
			h1.addItem( new Item( "h1-item0", h1 ) );
			h1.addItem( new Item( "h1-item1", h1 ) );
			entityManager.persist( h1 );
			return h1.getId();
		} );

		// Revision 2
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Header header = entityManager.find( Header.class, headerId );
			header.addItem( new Item( "h1-item2", header ) );
			entityManager.merge( header );
		} );

		// Revision 3
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Header header = entityManager.find( Header.class, headerId );
			header.removeItem( header.getEmbeddableWithCollection().getItems().get( 0 ) );
			entityManager.merge( header );
		} );

		// Revision 4
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Header header = entityManager.find( Header.class, headerId );
			header.setEmbeddableWithCollection( null );
			entityManager.merge( header );
		} );
	}
