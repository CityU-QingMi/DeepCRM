	private void saveUpdateAndRemoveEntity(EntityManager entityManager, Integer id) {
		EntityTransaction transaction = entityManager.getTransaction();

		transaction.begin();
		IntNoAutoIdTestEntity entity = new IntNoAutoIdTestEntity( 0, id );
		entityManager.persist( entity );
		assertEquals( id, entity.getId() );
		transaction.commit();

		transaction.begin();
		entity = entityManager.find( IntNoAutoIdTestEntity.class, id );
		entity.setNumVal( 1 );
		entity = entityManager.merge( entity );
		assertEquals( id, entity.getId() );
		transaction.commit();

		transaction.begin();
		entity = entityManager.find( IntNoAutoIdTestEntity.class, id );
		assertNotNull( entity );
		entityManager.remove( entity );
		transaction.commit();
	}
