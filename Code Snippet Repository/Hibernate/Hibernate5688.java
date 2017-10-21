	@Test
	public void testByteArray() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		CriteriaQuery<Order> orderCriteria = builder.createQuery( Order.class );
		Root<Order> orderRoot = orderCriteria.from( Order.class );
		
		orderCriteria.select( orderRoot );
		Predicate p = builder.equal( orderRoot.get( "number" ), new byte[]{'1','2'} );
		orderCriteria.where( p );

		List<Order> orders = em.createQuery( orderCriteria ).getResultList();
		assertTrue( orders.size() == 0 );
		em.getTransaction().commit();
		em.close();
	}
