	@Test
	@TestForIssue( jiraKey = "")
	public void fetchAttributeNodeFromSubgraph() {
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

		final EntityGraph<CustomerOrder> entityGraph = em.createEntityGraph( CustomerOrder.class );
		entityGraph.addAttributeNodes( "shippingAddress", "orderDate" );
		entityGraph.addAttributeNodes( "shippingAddress" );

		final Subgraph<OrderPosition> orderProductsSubgraph = entityGraph.addSubgraph( "orderPosition" );
		orderProductsSubgraph.addAttributeNodes( "amount" );
		orderProductsSubgraph.addAttributeNodes( "product" );

		final Subgraph<Product> productSubgraph = orderProductsSubgraph.addSubgraph( "product" );
		productSubgraph.addAttributeNodes( "productName" );

		TypedQuery<CustomerOrder> query = em.createQuery(
				"SELECT o FROM EntityGraphUsingFetchGraphTest$CustomerOrder o", CustomerOrder.class
		);
		query.setHint( "javax.persistence.loadgraph", entityGraph );
		final List<CustomerOrder> results = query.getResultList();

		assertTrue( Hibernate.isInitialized( results ) );

		em.getTransaction().commit();
		em.close();
	}
