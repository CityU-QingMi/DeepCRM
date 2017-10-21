	@Test
	@Priority(10)
	public void initData() {
		EntityManager em = getEntityManager();

		// Revision 1
		em.getTransaction().begin();
		Producer producer = new Producer( 1, "Sony" );
		ItemId sonyId = new ItemId( "TV", 1, producer );
		Item item = new Item( sonyId, 100.50 );
		PurchaseOrder order = new PurchaseOrder( item, null );
		em.persist( producer );
		em.persist( item );
		em.persist( order );
		em.getTransaction().commit();

		// Revision 2
		em.getTransaction().begin();
		order = em.find( PurchaseOrder.class, order.getId() );
		order.setComment( "fragile" );
		order = em.merge( order );
		em.getTransaction().commit();

		// Revision 3
		em.getTransaction().begin();
		item = em.find( Item.class, sonyId );
		item.setPrice( 110.00 );
		em.getTransaction().commit();

		orderId = order.getId();
		itemId = sonyId;

		em.close();
	}
