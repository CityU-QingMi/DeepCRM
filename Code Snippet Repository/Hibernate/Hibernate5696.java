	@Test
	public void testNotMultipleOr() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Order> orderCriteria = builder.createQuery( Order.class );
		Root<Order> orderRoot = orderCriteria.from( Order.class );

		orderCriteria.select( orderRoot );
		Predicate p1 = builder.equal( orderRoot.get( "id" ), "order-1" );
		Predicate p2 = builder.equal( orderRoot.get( "id" ), "order-2" );
		Predicate p3 = builder.equal( orderRoot.get( "id" ), "order-3" );
		final Predicate compoundPredicate = builder.or( p1, p2, p3 ).not();
		// negated OR should become an AND
		assertEquals( Predicate.BooleanOperator.AND, compoundPredicate.getOperator() );
		orderCriteria.where( compoundPredicate );

		List<Order> orders = em.createQuery( orderCriteria ).getResultList();
		assertEquals( 0, orders.size() );
		em.getTransaction().commit();
		em.close();
	}
