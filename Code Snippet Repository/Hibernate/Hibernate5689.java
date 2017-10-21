	@Test
	@TestForIssue( jiraKey = "" )
	public void testQuotientConversion() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Order> orderCriteria = builder.createQuery( Order.class );
		Root<Order> orderRoot = orderCriteria.from( Order.class );

		Long longValue = 999999999L;
		Path<Double> doublePath = orderRoot.get( Order_.totalPrice );
		Path<Integer> integerPath = orderRoot.get( Order_.customer ).get( Customer_.age );

		orderCriteria.select( orderRoot );
		Predicate p = builder.ge(
				builder.quot( integerPath, doublePath ),
				longValue
		);
		orderCriteria.where( p );

		List<Order> orders = em.createQuery( orderCriteria ).getResultList();
		assertTrue( orders.size() == 0 );
		em.getTransaction().commit();
		em.close();
	}
