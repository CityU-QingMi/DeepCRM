	@Test
	@TestForIssue(jiraKey = "")
	public void testNullLiteral() throws Exception {
		final EntityManager entityManager = getOrCreateEntityManager();
		try {
			entityManager.getTransaction().begin();

			final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			final CriteriaQuery<MyEntityDTO> query = criteriaBuilder.createQuery( MyEntityDTO.class );
			final Root<MyEntity> entity = query.from( MyEntity.class );

			query.multiselect( criteriaBuilder.literal( false ), criteriaBuilder.nullLiteral( String.class ) );

			final List<MyEntityDTO> dtos = entityManager.createQuery( query ).getResultList();

			assertThat( dtos.size(), is( 1 ) );
			assertThat( dtos.get( 0 ).active, is( false ) );
			assertThat( dtos.get( 0 ).name, nullValue() );
			assertThat( dtos.get( 0 ).surname, nullValue() );

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
