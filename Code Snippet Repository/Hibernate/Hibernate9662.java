	@Test
	public void testMetamodelContainsHbmVersion() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final TestEntity entity = new TestEntity();
			entity.setName( "Chris" );
			entityManager.persist( entity );
		} );

		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			final CriteriaQuery<TestEntity> query = builder.createQuery( TestEntity.class );
			final Root<TestEntity> root = query.from( TestEntity.class );

			assertThat( root.get( "version" ), is( notNullValue() ) );
		} );
	}
