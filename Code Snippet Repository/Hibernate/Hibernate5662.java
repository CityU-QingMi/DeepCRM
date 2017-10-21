	@Test
	public void testSelectCaseWithConcat() throws Exception {
		EntityManager entityManager = getOrCreateEntityManager();
		entityManager.getTransaction().begin();
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Object[]> query = cb.createQuery( Object[].class );
			Root<TestEntity> testEntity = query.from( TestEntity.class );

			query.multiselect(
					cb.selectCase()
							.when( cb.isNotNull( testEntity.get( "id" ) ), cb.concat( "test", cb.literal( "_1" ) ) )
							.otherwise( cb.literal( "Empty" ) ),
					cb.trim( cb.concat( ".", cb.literal( "Test   " ) ) )
			);

			final List<Object[]> results = entityManager.createQuery( query ).getResultList();
			assertThat( results.size(), is( 1 ) );
			assertThat( results.get( 0 )[0], is( "test_1" ) );
			assertThat( results.get( 0 )[1], is( ".Test" ) );
			entityManager.getTransaction().commit();
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
