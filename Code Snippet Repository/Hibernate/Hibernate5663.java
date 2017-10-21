	@Test
	public void testConcat() throws Exception {
		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery query = cb.createQuery();
			Root<TestEntity> testEntity = query.from( TestEntity.class );

			query.select( testEntity ).where( cb.equal( testEntity.get( "name" ), cb.concat( "test", cb.literal( "_1" ) ) ) );

			final List results = entityManager.createQuery( query ).getResultList();
			entityManager.getTransaction().commit();

			assertThat( results.size(), is( 1 ) );
		}
		catch (Exception e) {
			if ( entityManager.getTransaction().isActive() ) {
				entityManager.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			entityManager.close();
		}
	}
