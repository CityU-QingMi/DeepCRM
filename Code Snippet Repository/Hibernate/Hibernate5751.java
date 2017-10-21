	@Test
	public void testIllegalDereference() {
		EntityManager em = getOrCreateEntityManager();
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Order> criteria = criteriaBuilder.createQuery( Order.class );
			Root<Order> orderRoot = criteria.from( Order.class );
			Path simplePath = orderRoot.get( "totalPrice" );
			// this should cause an ISE...
			try {
				simplePath.get( "yabbadabbado" );
				fail( "Attempt to dereference basic path should throw IllegalStateException" );
			}
			catch (IllegalStateException expected) {
			}
		}
		finally {
			em.close();
		}
	}
