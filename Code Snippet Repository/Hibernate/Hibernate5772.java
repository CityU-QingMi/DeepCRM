	@Test
	public void testCaseInOrderBy() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder builder = em.getCriteriaBuilder();

		CriteriaQuery<Customer> query = builder.createQuery( Customer.class );
		Root<Customer> root = query.from( Customer.class );
		query.select( root );

		Path<String> emailPath = root.get( "email" );
		SimpleCase<String, Integer> orderCase = builder.selectCase( emailPath );
		orderCase = orderCase.when( "test@test.com", 1 );
		orderCase = orderCase.when( "test2@test.com", 2 );

		query.orderBy( builder.asc( orderCase.otherwise( 0 ) ) );

		em.createQuery( query );

	}
