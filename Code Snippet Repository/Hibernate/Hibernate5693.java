	@Test
	public void testSimpleNot() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Order> orderCriteria = builder.createQuery( Order.class );
		Root<Order> orderRoot = orderCriteria.from( Order.class );

		orderCriteria.select( orderRoot );
		final Predicate p = builder.not( builder.equal( orderRoot.get( "id" ), "order-1" ) );
		assertEquals( Predicate.BooleanOperator.AND, p.getOperator() );
		orderCriteria.where( p );

		List<Order> orders = em.createQuery( orderCriteria ).getResultList();
		assertEquals( 2, orders.size() );
		em.getTransaction().commit();
		em.close();
	}
