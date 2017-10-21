	@Test
	@TestForIssue( jiraKey = "")
	public void testBidirectonalKeyManyToOneId() {
		Product product = new Product();
		product.setName( "Product 1" );

		Session session = openSession();
		session.beginTransaction();
		session.save( product );
		session.getTransaction().commit();
		session.close();

		Order order = new Order();
		order.setName( "Order 1" );
		order.addLineItem( product, 2 );

		session = openSession();
		session.beginTransaction();
		session.save( order );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		OrderLine orderLine = order.getLineItems().iterator().next();
		orderLine.setAmount( 5 );
		OrderLine orderLineGotten = session.get( OrderLine.class, orderLine );
		assertSame( orderLineGotten, orderLine );
		assertEquals( Integer.valueOf( 2 ), orderLineGotten.getAmount() );
		SessionImplementor si = (SessionImplementor) session;
		assertTrue( si.getPersistenceContext().isEntryFor( orderLineGotten ) );
		assertFalse( si.getPersistenceContext().isEntryFor( orderLineGotten.getOrder() ) );
		assertFalse( si.getPersistenceContext().isEntryFor( orderLineGotten.getProduct() ) );
		session.getTransaction().commit();
		session.close();
	}
