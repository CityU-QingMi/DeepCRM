	@Test
	public void testImplicitJoinFromExplicitCollectionJoin() {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Order> criteria = criteriaBuilder.createQuery( Order.class );
		Root<Order> orderRoot = criteria.from( Order.class );
		Join<Order, LineItem> lineItemsJoin = orderRoot.join( Order_.lineItems );
		criteria.where( criteriaBuilder.lt( lineItemsJoin.get( LineItem_.quantity ), 2 ) );
		criteria.select( orderRoot ).distinct( true );
		TypedQuery<Order> query = em.createQuery( criteria );
		query.getResultList();

		em.getTransaction().commit();
		em.close();
	}
