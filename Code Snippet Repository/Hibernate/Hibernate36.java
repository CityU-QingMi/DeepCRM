	private void withBatch() {
		int entityCount = 100;
		//tag::batch-session-batch-insert-example[]
		EntityManager entityManager = null;
		EntityTransaction txn = null;
		try {
			entityManager = entityManagerFactory().createEntityManager();

			txn = entityManager.getTransaction();
			txn.begin();

			int batchSize = 25;

			for ( int i = 0; i < entityCount; ++i ) {
				Person Person = new Person( String.format( "Person %d", i ) );
				entityManager.persist( Person );

				if ( i > 0 && i % batchSize == 0 ) {
					//flush a batch of inserts and release memory
					entityManager.flush();
					entityManager.clear();
				}
			}

			txn.commit();
		} catch (RuntimeException e) {
			if ( txn != null && txn.isActive()) txn.rollback();
				throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		//end::batch-session-batch-insert-example[]
	}
