	private void assertQueryRowCount(String queryString, int rowCount) {
		EntityManager entityManager = getOrCreateEntityManager();
		try {
			// persist the data
			entityManager.getTransaction().begin();
			entityManager.persist( new Simple( new SimpleId( 1, 1 ), 1 ) );
			entityManager.getTransaction().commit();

			Query query = entityManager.createQuery( queryString );
			assertEquals( rowCount, query.getResultList().size() );
		}
		catch ( Exception e ) {
			if ( entityManager.getTransaction().isActive() ) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			entityManager.close();
		}
	}
