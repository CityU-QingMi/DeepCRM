	@Test
	public void testConfiguredDiscriminatorValue() {
		final ChildEntity childEntity = new ChildEntity( 1, "Child" );
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.save( childEntity );
		} );

		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			ChildEntity ce = session.find( ChildEntity.class, 1 );
			assertEquals( "ce", ce.getType() );
		} );
	}
