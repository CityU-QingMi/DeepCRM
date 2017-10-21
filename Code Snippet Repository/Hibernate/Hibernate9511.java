	@Test
	@TestForIssue( jiraKey = "")
	public void testSubselectInJoinedTable() {
		OrderEntry orderEntry1 = new OrderEntry();
		orderEntry1.setOrderEntryId( 1L );
		OrderEntry orderEntry2 = new OrderEntry();
		orderEntry2.setOrderEntryId( 2L );
		Order order = new Order();
		order.setOrderId( 3L );
		order.getOrderEntries().add( orderEntry1 );
		order.getOrderEntries().add( orderEntry2 );
		order.setFirstOrderEntry( orderEntry1 );

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( orderEntry1 );
		s.persist( orderEntry2 );
		s.persist( order );
		tx.commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		order = (Order) s.get( Order.class, order.getOrderId() );
		assertEquals( orderEntry1.getOrderEntryId(), order.getFirstOrderEntry().getOrderEntryId() );
		assertEquals( 2, order.getOrderEntries().size() );
		assertEquals( orderEntry1.getOrderEntryId(), order.getOrderEntries().get( 0 ).getOrderEntryId() );
		assertEquals( orderEntry2.getOrderEntryId(), order.getOrderEntries().get( 1 ).getOrderEntryId() );
		s.getTransaction().commit();
		s.close();

	}
