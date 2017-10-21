	@Test
	public void testComplicatedNotAnd() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Order> orderCriteria = builder.createQuery( Order.class );
		Root<Order> orderRoot = orderCriteria.from( Order.class );

		orderCriteria.select( orderRoot );
		Predicate p1 = builder.equal( orderRoot.get( "id" ), "order-1" );
		Predicate p2 = builder.equal( orderRoot.get( "id" ), "order-2" );
		Predicate compoundPredicate = builder.and( p1, p2 ).not();
		// a negated AND should become an OR
		assertEquals( Predicate.BooleanOperator.OR, compoundPredicate.getOperator() );
		orderCriteria.where( compoundPredicate );

		List<Order> orders = em.createQuery( orderCriteria ).getResultList();
		assertEquals( 3, orders.size() );
		em.getTransaction().commit();
		em.close();
	}
