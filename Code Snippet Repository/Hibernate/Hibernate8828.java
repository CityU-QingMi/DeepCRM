	protected void validateCustomerIndustries(Metadata metadata) {
		final Collection collectionBinding = metadata.getCollectionBinding( Customer.class.getName() + ".industries" );
		assertNotNull( collectionBinding );

		validateCustomerIndustriesTableName( collectionBinding.getCollectionTable().getQuotedName() );

		assertEquals( 1, collectionBinding.getKey().getColumnSpan() );
		validateCustomerIndustriesKeyColumn( (Column) collectionBinding.getKey().getColumnIterator().next() );

		assertEquals( 1, collectionBinding.getElement().getColumnSpan() );
		validateCustomerIndustriesElementColumn( (Column) collectionBinding.getElement().getColumnIterator().next() );
	}
