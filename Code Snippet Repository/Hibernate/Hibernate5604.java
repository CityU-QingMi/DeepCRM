	@Test
	public void testSerialization() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {

			CriteriaQuery<Order> criteria = entityManager.getCriteriaBuilder().createQuery( Order.class );
			Root<Order> root = criteria.from( Order.class );
			root.fetch( "lineItems" );
			criteria.select( root );

			criteria = serializeDeserialize( criteria );

			entityManager.createQuery( criteria ).getResultList();
		} );
	}
