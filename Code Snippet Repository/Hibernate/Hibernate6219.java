	@Test
	public void testBothGetterAndIsVariantAreIgnoredWhenMarkedTransient() {
		TransactionUtil.doInHibernate( this::sessionFactory, session1 -> {
			SecondTestEntity entity = new SecondTestEntity();
			entity.setId( 1L );
			entity.setChecked( true );
			session1.save( entity );
		} );

		TransactionUtil.doInHibernate( this::sessionFactory, session1 -> {
			final SecondTestEntity entity = session1.find( SecondTestEntity.class, 1L );
			assertThat( entity.getChecked(), is( nullValue() ) );
		} );
	}
