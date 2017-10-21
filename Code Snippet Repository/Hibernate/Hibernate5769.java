	@Test
	@TestForIssue(jiraKey = "")
	public void testCaseIntegerResult() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Tuple> query = builder.createTupleQuery();
		Root<Customer> root = query.from( Customer.class );

		Path<String> emailPath = root.get( "email" );
		CriteriaBuilder.Case<Integer> selectCase = builder.selectCase();
		selectCase.when( builder.greaterThan( builder.length( emailPath ), 13 ), 2 );
		selectCase.when( builder.greaterThan( builder.length( emailPath ), 12 ), 1 );
		Expression<Integer> emailType = selectCase.otherwise( 0 );

		query.multiselect( emailPath, emailType );

		em.createQuery( query ).getResultList();
	}
