	@Test
	@Priority(10)
	public void initData() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final EntityC c1 = new EntityC();
			c1.setId( 1 );
			c1.setName( "c1" );
			c1.setFoo( "foo" );
			c1.setPropB( "propB" );
			c1.setPropC( "propC" );
			entityManager.persist( c1 );

			final EntityA a1 = new EntityA();
			a1.setId( 1 );
			a1.setRelationToC( c1 );
			a1.setPropA( "propC" );
			entityManager.persist( a1 );
		} );
	}
