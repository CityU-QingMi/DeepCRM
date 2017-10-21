	@Test
	public void testRestrictedCorrelation() {
		CriteriaBuilder builder = entityManagerFactory().getCriteriaBuilder();
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaQuery<Order> criteria = builder.createQuery( Order.class );
		Root<Order> orderRoot = criteria.from( Order.class );
		criteria.select( orderRoot );
		// create correlated subquery
		Subquery<Customer> customerSubquery = criteria.subquery( Customer.class );
		Root<Order> orderRootCorrelation = customerSubquery.correlate( orderRoot );
		Join<Order, Customer> orderCustomerJoin = orderRootCorrelation.join( Order_.customer );
		customerSubquery.where( builder.like( orderCustomerJoin.get( Customer_.name ), "%Caruso" ) )
				.select( orderCustomerJoin );
		criteria.where( builder.exists( customerSubquery ) );
		em.createQuery( criteria ).getResultList();

		em.getTransaction().commit();
		em.close();
	}
