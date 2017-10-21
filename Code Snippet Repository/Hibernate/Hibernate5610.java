	@Test
	@TestForIssue(jiraKey = "")
	public void basicMultipleAssignments() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaUpdate<Customer> updateCriteria = builder.createCriteriaUpdate( Customer.class );
		updateCriteria.from( Customer.class );
		updateCriteria.set( Customer_.name, "Bob" );
		updateCriteria.set( Customer_.age, 99 );
		em.createQuery( updateCriteria ).executeUpdate();

		em.getTransaction().commit();
		em.close();
	}
