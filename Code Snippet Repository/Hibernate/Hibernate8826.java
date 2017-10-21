	protected void validateCustomerAddresses(Metadata metadata) {
		final Collection collectionBinding = metadata.getCollectionBinding( Customer.class.getName() + ".addresses" );
		assertNotNull( collectionBinding );

		validateCustomerAddressesTableName( collectionBinding.getCollectionTable().getQuotedName() );

		assertEquals( 1, collectionBinding.getKey().getColumnSpan() );
		validateCustomerAddressesKeyColumn( (Column) collectionBinding.getKey().getColumnIterator().next() );

		assertEquals( 3, collectionBinding.getElement().getColumnSpan() );
		validateCustomerAddressesElementComponent( assertTyping( Component.class, collectionBinding.getElement() ) );
	}
