	@Test
	public void testGetCorrelatedParentIllegalStateException() {
		// test that attempting to call getCorrelatedParent on a uncorrelated query/subquery
		// throws ISE

		CriteriaBuilder builder = entityManagerFactory().getCriteriaBuilder();
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaQuery<Customer> criteria = builder.createQuery( Customer.class );
		Root<Customer> customerRoot = criteria.from( Customer.class );
		Join<Customer, Order> orderJoin = customerRoot.join( Customer_.orders );
		criteria.select( customerRoot );
		Subquery<Double> subCriteria = criteria.subquery( Double.class );
		Root<Order> subqueryOrderRoot = subCriteria.from( Order.class );
		subCriteria.select( builder.min( subqueryOrderRoot.get( Order_.totalPrice ) ) );
		criteria.where( builder.equal( orderJoin.get( "totalPrice" ), builder.all( subCriteria ) ) );

		assertFalse( customerRoot.isCorrelated() );
		assertFalse( subqueryOrderRoot.isCorrelated() );

		try {
			customerRoot.getCorrelationParent();
			fail( "Should have resulted in IllegalStateException" );
		}
		catch (IllegalStateException expected) {
		}

		try {
			subqueryOrderRoot.getCorrelationParent();
			fail( "Should have resulted in IllegalStateException" );
		}
		catch (IllegalStateException expected) {
		}

		em.getTransaction().commit();
		em.close();
	}
