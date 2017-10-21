	@Test
	@RequiresDialectFeature( value = DialectChecks.DoesNotSupportFollowOnLocking.class)
	public void testEntityLockModeStateAfterQueryLocking() {
		// Create some test data
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( new LocalEntity( 1, "test" ) );
		em.getTransaction().commit();
//		em.close();

		// issue the query with locking
//		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery( "select l from LocalEntity l" );
		assertEquals( LockModeType.NONE, query.getLockMode() );
		query.setLockMode( LockModeType.PESSIMISTIC_READ );
		assertEquals( LockModeType.PESSIMISTIC_READ, query.getLockMode() );
		List<LocalEntity> results = query.getResultList();

		// and check the lock mode for each result
		for ( LocalEntity e : results ) {
			assertEquals( LockModeType.PESSIMISTIC_READ, em.getLockMode( e ) );
		}

		em.getTransaction().commit();
		em.close();

		// clean up test data
		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.createQuery( "delete from LocalEntity" ).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
