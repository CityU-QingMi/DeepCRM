	@Test
	@Priority(10)
	public void initData() throws Exception {
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		EntityManager entityManager = getEntityManager();
		try {
			TestEntity entity = new TestEntity( ENTITY_ID, "Fab" );
			entityManager.persist( entity );

			OtherTestEntity other = new OtherTestEntity( OTHER_ENTITY_ID, "other" );

			entity.addOther( other );
			entityManager.persist( entity );
			entityManager.persist( other );

		}
		finally {
			entityManager.close();
			TestingJtaPlatformImpl.tryCommit();
		}
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		entityManager = getEntityManager();
		try {
			TestEntity entity = entityManager.find( TestEntity.class, ENTITY_ID );
			OtherTestEntity other = entityManager.find( OtherTestEntity.class, OTHER_ENTITY_ID );
			entityManager.remove( entity );
			entityManager.remove( other );
		}
		finally {
			entityManager.close();
			TestingJtaPlatformImpl.tryCommit();
		}
	}
