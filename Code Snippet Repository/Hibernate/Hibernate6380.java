	@Test
	@TestForIssue(jiraKey = "")
	public void testManyToOneInCompositeIdClassInPC() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Order order = new Order();
		s.persist( order );
		Product product = new Product();
		product.name = "small car";
		s.persist( product );
		OrderLine orderLine = new OrderLine();
		orderLine.order = order;
		orderLine.product = product;
		s.persist( orderLine );
		s.flush();
		s.clear();

		s.clear();
		OrderLinePk orderLinePK = new OrderLinePk();
		orderLinePK.order = orderLine.order;
		orderLinePK.product = orderLine.product;
		orderLine = (OrderLine) s.get( OrderLine.class, orderLinePK );
		assertTrue( orderLine.order != orderLinePK.order );
		assertTrue( orderLine.product != orderLinePK.product );
		SessionImplementor sessionImplementor = (SessionImplementor) s;
		assertTrue( sessionImplementor.getPersistenceContext().isEntryFor( orderLine ) );
		assertTrue( sessionImplementor.getPersistenceContext().isEntryFor( orderLine.order ) );
		assertTrue( sessionImplementor.getPersistenceContext().isEntryFor( orderLine.product ) );
		assertFalse( sessionImplementor.getPersistenceContext().isEntryFor( orderLinePK.order ) );
		assertFalse( sessionImplementor.getPersistenceContext().isEntryFor( orderLinePK.product ) );
		tx.rollback();
		s.close();
	}
