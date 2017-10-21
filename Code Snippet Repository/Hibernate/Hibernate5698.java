	@Test
	public void testCharArray() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Order> orderCriteria = builder.createQuery( Order.class );
		Root<Order> orderRoot = orderCriteria.from( Order.class );
		
		orderCriteria.select( orderRoot );
		Predicate p = builder.equal( orderRoot.get( "domen" ), new char[]{'r','u'} );
		orderCriteria.where( p );

		List<Order> orders = em.createQuery( orderCriteria ).getResultList();
		assertTrue( orders.size() == 1 );
		em.getTransaction().commit();
		em.close();
	}
