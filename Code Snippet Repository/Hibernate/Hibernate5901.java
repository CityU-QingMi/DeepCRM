	@Test
	@TestForIssue( jiraKey = "" )
	public void testNoneLockModeForNonSelectQueryAllowed() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		org.hibernate.query.Query query = em.createQuery( "delete from Lockable l" ).unwrap( org.hibernate.query.Query.class );

		assertEquals( LockMode.NONE, query.getLockOptions().getLockMode() );

		query.setLockMode( LockModeType.NONE );

		em.getTransaction().commit();
		em.clear();

		// ensure other modes still throw the exception
		em.getTransaction().begin();
		query = em.createQuery( "delete from Lockable l" ).unwrap( org.hibernate.query.Query.class );
		assertEquals( LockMode.NONE, query.getLockOptions().getLockMode() );

		try {
			// Throws IllegalStateException
			query.setLockMode( LockModeType.PESSIMISTIC_WRITE );
			fail( "IllegalStateException should have been thrown." );
		}
		catch (IllegalStateException e) {
			// expected
		}
		finally {
			em.getTransaction().rollback();
			em.close();
		}
	}
