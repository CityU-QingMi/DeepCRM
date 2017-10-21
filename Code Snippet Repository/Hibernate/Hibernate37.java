	private void withScroll() {
		withBatch();

		//tag::batch-session-scroll-example[]
		EntityManager entityManager = null;
		EntityTransaction txn = null;
		ScrollableResults scrollableResults = null;
		try {
			entityManager = entityManagerFactory().createEntityManager();

			txn = entityManager.getTransaction();
			txn.begin();

			int batchSize = 25;

			Session session = entityManager.unwrap( Session.class );

			scrollableResults = session
				.createQuery( "select p from Person p" )
				.setCacheMode( CacheMode.IGNORE )
				.scroll( ScrollMode.FORWARD_ONLY );

			int count = 0;
			while ( scrollableResults.next() ) {
				Person Person = (Person) scrollableResults.get( 0 );
				processPerson(Person);
				if ( ++count % batchSize == 0 ) {
					//flush a batch of updates and release memory:
					entityManager.flush();
					entityManager.clear();
				}
			}

			txn.commit();
		} catch (RuntimeException e) {
			if ( txn != null && txn.isActive()) txn.rollback();
				throw e;
		} finally {
			if (scrollableResults != null) {
				scrollableResults.close();
			}
			if (entityManager != null) {
				entityManager.close();
			}
		}
		//end::batch-session-scroll-example[]
	}
