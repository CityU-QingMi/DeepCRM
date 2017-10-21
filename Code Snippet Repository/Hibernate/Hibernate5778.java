	@Test
	@TestForIssue(jiraKey = "")
	public void testCorrelatedJoinsFromSubquery() {
		CriteriaBuilder builder = entityManagerFactory().getCriteriaBuilder();
		CriteriaQuery<Customer> cquery = builder.createQuery(Customer.class);
        Root<Customer> customer = cquery.from(Customer.class);
        cquery.select(customer);
        Subquery<Order> sq = cquery.subquery(Order.class);
        Join<Customer, Order> sqo = sq.correlate(customer.join(Customer_.orders));
        sq.select(sqo);
        Set<Join<?, ?>> cJoins = sq.getCorrelatedJoins();
        
        // ensure the join is returned in #getCorrelatedJoins
        assertEquals( cJoins.size(), 1);
	}
