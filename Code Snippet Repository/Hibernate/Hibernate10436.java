	@Test
	@Priority(10)
	public void initData() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Simple simple = new Simple( 1, "Simple" );
			simple.getEmbeddedMap().put( "1", "One" );
			simple.getEmbeddedMap().put( "2", "Two" );
			entityManager.persist( simple );
		} );

		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Simple simple = entityManager.find( Simple.class, 1 );
			simple.getEmbeddedMap().put( "3", "Three" );
			entityManager.merge( simple );
		} );

		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Simple simple = entityManager.find( Simple.class, 1 );
			simple.getEmbeddedMap().remove( "1" );
			simple.getEmbeddedMap().remove( "2" );
			entityManager.merge( simple );
		} );

		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Simple simple = entityManager.find( Simple.class, 1 );
			simple.getEmbeddedMap().remove( "3" );
			simple.getEmbeddedMap().put( "3", "Three-New" );
			entityManager.merge( simple );
		} );

		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Simple simple = entityManager.find( Simple.class, 1 );
			simple.getEmbeddedMap().clear();
			entityManager.merge( simple );
		} );
	}
