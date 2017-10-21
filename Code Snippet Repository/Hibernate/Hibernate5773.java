	@Test
	public void testCaseInOrderBy2() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Customer> query = builder.createQuery( Customer.class );
		Root<Customer> root = query.from( Customer.class );
		query.select( root );

		Path<String> emailPath = root.get( "email" );
		SimpleCase<String, String> orderCase = builder.selectCase( emailPath );
		orderCase = orderCase.when( "test@test.com", "a" );
		orderCase = orderCase.when( "test2@test.com", "b" );

		query.orderBy( builder.asc( orderCase.otherwise( "c" ) ) );

		em.createQuery( query );

	}
