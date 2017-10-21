	@Test
	@TestForIssue( jiraKey = "")
	public void fetchUsingHql() {
		// This test is here only for comparison with results from fetchAttributeNodeFromSubgraph.
		// At the time this was written, the generated SQL from the HQL is the same as that generated with the
		// query hint in fetchAttributeNodeFromSubgraph. I am leaving this here for future debugging purposes.
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();

		Address address = new Address();
		address.city = "TestCity";

		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.shippingAddress = address;

		Product product = new Product();

		OrderPosition orderPosition = new OrderPosition();
		orderPosition.product = product;

		customerOrder.orderPosition = orderPosition;
		em.persist( address );
		em.persist( orderPosition );
		em.persist( product );
		em.persist( customerOrder );

		em.getTransaction().commit();
		em.clear();

		em.getTransaction().begin();

		TypedQuery<CustomerOrder> query = em.createQuery(
				"SELECT o FROM EntityGraphUsingFetchGraphTest$CustomerOrder o left join fetch o.orderPosition pos left join fetch pos.product left join fetch o.shippingAddress", CustomerOrder.class
		);
		final List<CustomerOrder> results = query.getResultList();

		assertTrue( Hibernate.isInitialized( results ) );

		em.getTransaction().commit();
		em.close();
	}
