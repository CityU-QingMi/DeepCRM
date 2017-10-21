	@Test
	public void testSimpleJoinCriteria() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {

			// String based...
			CriteriaQuery<Order> criteria = entityManager.getCriteriaBuilder().createQuery( Order.class );
			Root<Order> root = criteria.from( Order.class );
			root.join( "lineItems" );
			criteria.select( root );
			entityManager.createQuery( criteria ).getResultList();
		});
	}
