	@Test
	public void testManyToOneNonPk() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Order order = new Order();
		order.setOrderNbr( "123" );
		s.persist( order );
		OrderLine ol = new OrderLine();
		ol.setItem( "Mouse" );
		ol.setOrder( order );
		s.persist( ol );
		s.flush();
		s.clear();
		ol = (OrderLine) s.get( OrderLine.class, ol.getId() );
		assertNotNull( ol.getOrder() );
		assertEquals( "123", ol.getOrder().getOrderNbr() );
		assertTrue( ol.getOrder().getOrderLines().contains( ol ) );
		tx.rollback();
		s.close();
	}
