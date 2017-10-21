	protected void validateCustomerOrders(Metadata metadata) {
		final Collection collectionBinding = metadata.getCollectionBinding( Customer.class.getName() + ".orders" );
		assertNotNull( collectionBinding );

		validateCustomerOrdersTableName( collectionBinding.getCollectionTable().getQuotedName() );

		assertEquals( 1, collectionBinding.getKey().getColumnSpan() );
		validateCustomerOrdersKeyColumn( (Column) collectionBinding.getKey().getColumnIterator().next() );

		assertEquals( 1, collectionBinding.getElement().getColumnSpan() );
		validateCustomerOrdersElementColumn( (Column) collectionBinding.getElement().getColumnIterator().next() );
	}
