	@Test
	public void testGetAndIsVariantCanHaveDifferentReturnValueWhenOneHasATransientAnnotation() {
		TransactionUtil.doInHibernate( this::sessionFactory, session1 -> {
			TestEntity entity = new TestEntity();
			entity.setId( 1L );
			entity.setChecked( true );
			session1.save( entity );
		} );

		TransactionUtil.doInHibernate( this::sessionFactory, session1 -> {
			final TestEntity entity = session1.find( TestEntity.class, 1L );
			assertThat( entity.isChecked(), is( true ) );
		} );

		TransactionUtil.doInHibernate( this::sessionFactory, session1 -> {
			final TestEntity entity = session1.find( TestEntity.class, 1L );
			entity.setChecked( null );
		} );

		TransactionUtil.doInHibernate( this::sessionFactory, session1 -> {
			final TestEntity entity = session1.find( TestEntity.class, 1L );
			assertThat( entity.isChecked(), is( nullValue() ) );
		} );
	}
