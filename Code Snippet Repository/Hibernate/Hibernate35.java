	private void withoutBatch() {
		//tag::batch-session-batch-example[]
		EntityManager entityManager = null;
		EntityTransaction txn = null;
		try {
			entityManager = entityManagerFactory().createEntityManager();

			txn = entityManager.getTransaction();
			txn.begin();

			for ( int i = 0; i < 100_000; i++ ) {
				Person Person = new Person( String.format( "Person %d", i ) );
				entityManager.persist( Person );
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
		//end::batch-session-batch-example[]
	}
