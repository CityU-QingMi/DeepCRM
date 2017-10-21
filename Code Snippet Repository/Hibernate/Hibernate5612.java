	@Test
	public void basicTest() {
		EntityManager em = getOrCreateEntityManager();
		CriteriaQuery<Order> criteria = em.getCriteriaBuilder().createQuery( Order.class );
		Root<Order> root = criteria.from( Order.class );
		criteria.select( root );
		CollectionJoin<Order,LineItem> lineItemsJoin = root.join( Order_.lineItems );
		lineItemsJoin.on(
				em.getCriteriaBuilder().gt(
						lineItemsJoin.get( LineItem_.quantity ),
						em.getCriteriaBuilder().literal( 20 )
				)
		);
		em.createQuery( criteria ).getResultList();
		em.close();
	}
