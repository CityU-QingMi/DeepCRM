	@Test
	@TestForIssue( jiraKey = "")
	public void testNullLiteralExpressionInCriteriaUpdate() {
		EntityManager entityManager = getOrCreateEntityManager();
		try {
			entityManager.getTransaction().begin();

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaUpdate<Person> criteriaUpdate = builder.createCriteriaUpdate( Person.class );
			criteriaUpdate.from(Person.class);
			criteriaUpdate.set( Person_.subject, builder.nullLiteral( Subject.class ) );

			entityManager.createQuery( criteriaUpdate ).executeUpdate();

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
