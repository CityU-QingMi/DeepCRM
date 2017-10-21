	@Override
	protected void prepareTest() throws Exception {
		TestEntity entity = new TestEntity();
		doInHibernate( this::sessionFactory, session -> {

						   entity.setFirstLobField( value1 );
						   entity.setSecondLobField( value2 );
						   entity.setClobField( session.getLobHelper().createClob( value2 ) );
						   session.save( entity );
					   } );

		doInHibernate( this::sessionFactory, session -> {
						   final TestEntity testEntity = session.find( TestEntity.class, entity.getId() );
						   assertThat( testEntity.getFirstLobField(), is( value1 ) );
					   } );
	}
