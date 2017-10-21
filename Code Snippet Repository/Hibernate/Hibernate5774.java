	@Test
	public void testBasicCorrelation() {
		CriteriaBuilder builder = entityManagerFactory().getCriteriaBuilder();
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaQuery<Customer> criteria = builder.createQuery( Customer.class );
		Root<Customer> customer = criteria.from( Customer.class );
		criteria.select( customer );
		Subquery<Order> orderSubquery = criteria.subquery( Order.class );
		Root<Customer> customerCorrelationRoot = orderSubquery.correlate( customer );
		Join<Customer, Order> customerOrderCorrelationJoin = customerCorrelationRoot.join( Customer_.orders );
		orderSubquery.select( customerOrderCorrelationJoin );
		criteria.where( builder.not( builder.exists( orderSubquery ) ) );
		em.createQuery( criteria ).getResultList();

		em.getTransaction().commit();
		em.close();
	}
