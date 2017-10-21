	@Test
	public void testConnectionPoolDoesNotConsumeAllConnections() {
		for ( int i = 0; i < CONNECTION_POOL_SIZE + 1; ++i ) {
			EntityManager entityManager = getOrCreateEntityManager();
			try {
				for ( int j = 0; j < 2; j++ ) {
					try {
						final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
						final CriteriaQuery<TestEntity> criteriaQuery = builder.createQuery(
								TestEntity.class );
						criteriaQuery.select( criteriaQuery.from( TestEntity.class ) );

						entityManager.createQuery( criteriaQuery ).getResultList();
					}
					catch ( PersistenceException e ) {
						if ( e.getCause() instanceof SQLGrammarException ) {
							//expected, the schema was not created
						}
						else {
							throw e;
						}
					}
				}
			}
			finally {
				entityManager.close();
			}
		}
	}
