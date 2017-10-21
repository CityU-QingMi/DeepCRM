	protected void validateCustomerRegisteredTrademarks(Metadata metadata) {
		final Collection collectionBinding = metadata.getCollectionBinding( Customer.class.getName() + ".registeredTrademarks" );
		assertNotNull( collectionBinding );

		validateCustomerRegisteredTrademarksTableName( collectionBinding.getCollectionTable().getQuotedName() );

		assertEquals( 1, collectionBinding.getKey().getColumnSpan() );
		validateCustomerRegisteredTrademarksKeyColumn( (Column) collectionBinding.getKey().getColumnIterator().next() );

		assertEquals( 1, collectionBinding.getElement().getColumnSpan() );
		validateCustomerRegisteredTrademarksElementColumn(
				(Column) collectionBinding.getElement()
						.getColumnIterator()
						.next()
		);
	}
