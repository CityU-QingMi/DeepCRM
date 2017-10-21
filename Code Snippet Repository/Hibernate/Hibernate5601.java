	@Test
	public void testJustSimpleRootCriteria() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			// First w/o explicit selection...
			CriteriaQuery<Customer> criteria = entityManager.getCriteriaBuilder().createQuery( Customer.class );
			criteria.from( Customer.class );
			entityManager.createQuery( criteria ).getResultList();

			// Now with...
			criteria = entityManager.getCriteriaBuilder().createQuery( Customer.class );
			Root<Customer> root = criteria.from( Customer.class );
			criteria.select( root );
			entityManager.createQuery( criteria ).getResultList();
		});
	}
