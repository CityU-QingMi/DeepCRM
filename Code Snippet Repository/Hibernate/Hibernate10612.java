	@Test
	public void testHistoryOfPurchaseOrder() {
		PurchaseOrder ver1 = new PurchaseOrder(
				orderId, new Item(
				new ItemId( "TV", 1, new Producer( 1, "Sony" ) ),
				100.50
		), null
		);
		PurchaseOrder ver2 = new PurchaseOrder(
				orderId, new Item(
				new ItemId( "TV", 1, new Producer( 1, "Sony" ) ),
				100.50
		), "fragile"
		);

		Assert.assertEquals( ver1, getAuditReader().find( PurchaseOrder.class, orderId, 1 ) );
		Assert.assertEquals( ver2, getAuditReader().find( PurchaseOrder.class, orderId, 2 ) );
	}
