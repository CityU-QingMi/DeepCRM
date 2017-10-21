	@Test
	@TestForIssue(jiraKey = "")
	public void testCaseStringResult() {
		EntityManager em = getOrCreateEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Tuple> query = builder.createTupleQuery();
		Root<Customer> root = query.from( Customer.class );

		Path<String> emailPath = root.get( "email" );
		CriteriaBuilder.Case<String> selectCase = builder.selectCase();
		selectCase.when( builder.greaterThan( builder.length( emailPath ), 13 ), "Long" );
		selectCase.when( builder.greaterThan( builder.length( emailPath ), 12 ), "Normal" );
		Expression<String> emailType = selectCase.otherwise( "Unknown" );

		query.multiselect( emailPath, emailType );

		em.createQuery( query ).getResultList();
	}
