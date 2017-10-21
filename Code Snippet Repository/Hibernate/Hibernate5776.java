	@Test
	@SkipForDialect(value=SybaseASE15Dialect.class, jiraKey="")
	public void testCorrelationExplicitSelectionCorrelation() {
		CriteriaBuilder builder = entityManagerFactory().getCriteriaBuilder();
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		CriteriaQuery<Customer> customerCriteria = builder.createQuery( Customer.class );
		Root<Customer> customer = customerCriteria.from( Customer.class );
		Join<Customer, Order> o = customer.join( Customer_.orders );
		Subquery<Order> sq = customerCriteria.subquery(Order.class);
		Join<Customer, Order> sqo = sq.correlate(o);
		Join<Order, LineItem> sql = sqo.join(Order_.lineItems);
		sq.where( builder.gt(sql.get( LineItem_.quantity), 3) );
		// use the correlation itself as the subquery selection (initially caused problems wrt aliases)
		sq.select(sqo);
		customerCriteria.select(customer).distinct(true);
		customerCriteria.where(builder.exists(sq));
		em.createQuery( customerCriteria ).getResultList();

		em.getTransaction().commit();
		em.close();
	}
