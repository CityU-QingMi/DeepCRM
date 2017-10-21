	@Test
	@Priority(10)
	public void initData() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final EntityC c = new EntityC();
			c.setId( 1 );
			c.setFoo( "bar" );
			entityManager.persist( c );

			final EntityD d = new EntityD();
			d.setId( 1 );
			d.setFoo( "bar" );
			entityManager.persist( d );

			final EntityB b1 = new EntityB();
			b1.setId( 1 );
			b1.setName( "b1" );
			b1.setRelationToC( c );
			b1.setRelationToD( d );
			entityManager.persist( b1 );

			final EntityB b2 = new EntityB();
			b2.setId( 2 );
			b2.setName( "b2" );
			b2.setRelationToC( c );
			b2.setRelationToD( d );
			entityManager.persist( b2 );
		} );
	}
