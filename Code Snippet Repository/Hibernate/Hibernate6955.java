	@Test
	public void testManyToOneReferenceManyToOne() throws Exception {
		Item item = new Item();
		item.setId( 1 );
		Vendor vendor = new Vendor();
		vendor.setId( 1 );
		ItemCost cost = new ItemCost();
		cost.setCost( new BigDecimal(1) );
		cost.setId( 1 );
		cost.setItem( item );
		cost.setVendor( vendor );
		WarehouseItem wItem = new WarehouseItem();
		wItem.setDefaultCost( cost );
		wItem.setId( 1 );
		wItem.setItem( item );
		wItem.setQtyInStock( new BigDecimal(1) );
		wItem.setVendor( vendor );
		Session s = openSession( );
		s.getTransaction().begin();
		s.persist( item );
		s.persist( vendor );
		s.persist( cost );
		s.persist( wItem );
		s.flush();
		s.clear();
		wItem = (WarehouseItem) s.get(WarehouseItem.class, wItem.getId() );
		assertNotNull( wItem.getDefaultCost().getItem() );
		s.getTransaction().rollback();
		s.close();
	}
