	@Test
	public void testManyToOneNonPkSecondaryTable() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Order order = new Order();
		order.setOrderNbr( "123" );
		s.persist( order );
		OrderLine ol = new OrderLine();
		ol.setItem( "Mouse" );
		ol.setReplacementOrder( order );
		s.persist( ol );
		s.flush();
		s.clear();
		ol = (OrderLine) s.get( OrderLine.class, ol.getId() );
		assertNotNull( ol.getReplacementOrder() );
		assertEquals( "123", ol.getReplacementOrder().getOrderNbr() );
		assertFalse( ol.getReplacementOrder().getOrderLines().contains( ol ) );
		tx.rollback();
		s.close();
	}
