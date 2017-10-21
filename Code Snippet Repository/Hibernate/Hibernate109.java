	@Test
	public void testFlushAutoCommit() {
		EntityManager entityManager = null;
		EntityTransaction txn = null;
		try {
			//tag::flushing-auto-flush-commit-example[]
			entityManager = entityManagerFactory().createEntityManager();
			txn = entityManager.getTransaction();
			txn.begin();

			Person person = new Person( "John Doe" );
			entityManager.persist( person );
			log.info( "Entity is in persisted state" );

			txn.commit();
			//end::flushing-auto-flush-commit-example[]
		} catch (RuntimeException e) {
			if ( txn != null && txn.isActive()) txn.rollback();
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
	}
