	@Test
	public void testComplicatedNotOr() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Order> orderCriteria = builder.createQuery( Order.class );
		Root<Order> orderRoot = orderCriteria.from( Order.class );

		orderCriteria.select( orderRoot );
		Predicate p1 = builder.equal( orderRoot.get( "id" ), "order-1" );
		Predicate p2 = builder.equal( orderRoot.get( "id" ), "order-2" );
		Predicate compoundPredicate = builder.not( builder.or( p1, p2 ) );
		// negated OR should become an AND
		assertEquals( Predicate.BooleanOperator.AND, compoundPredicate.getOperator() );
		orderCriteria.where( compoundPredicate );

		List<Order> orders = em.createQuery( orderCriteria ).getResultList();
		assertEquals( 1, orders.size() );
		Order order = orders.get( 0 );
		assertEquals( "order-3", order.getId() );
		em.getTransaction().commit();
		em.close();
	}
