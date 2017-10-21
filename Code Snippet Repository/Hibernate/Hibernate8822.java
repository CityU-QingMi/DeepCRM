	protected void validateCustomer(Metadata metadata) {
		final PersistentClass customerBinding = metadata.getEntityBinding( Customer.class.getName() );
		assertNotNull( customerBinding );

		validateCustomerPrimaryTableName( customerBinding.getTable().getQuotedName() );

		assertEquals( 1, customerBinding.getIdentifier().getColumnSpan() );
		validateCustomerPrimaryKeyColumn( (Column) customerBinding.getIdentifier().getColumnIterator().next() );

		assertNotNull( customerBinding.getVersion() );
		assertEquals( 1, customerBinding.getVersion().getColumnSpan() );
		validateCustomerVersionColumn( (Column) customerBinding.getVersion().getColumnIterator().next() );

		final Property nameBinding = customerBinding.getProperty( "name" );
		assertNotNull( nameBinding );
		assertEquals( 1, nameBinding.getColumnSpan() );
		validateCustomerNameColumn( (Column) nameBinding.getColumnIterator().next() );

		final Property hqAddressBinding = customerBinding.getProperty( "hqAddress" );
		assertNotNull( hqAddressBinding );
		assertEquals( 3, hqAddressBinding.getColumnSpan() );
		validateCustomerHqAddressComponent( assertTyping( Component.class, hqAddressBinding.getValue() ) );
	}
