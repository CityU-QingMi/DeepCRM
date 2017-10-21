	protected void validateOrder(Metadata metadata) {
		final PersistentClass orderBinding = metadata.getEntityBinding( Order.class.getName() );
		assertNotNull( orderBinding );

		validateOrderPrimaryTableName( orderBinding.getTable().getQuotedName() );

		assertEquals( 1, orderBinding.getIdentifier().getColumnSpan() );
		validateOrderPrimaryKeyColumn( (Column) orderBinding.getIdentifier().getColumnIterator().next() );

		final Property referenceCodeBinding = orderBinding.getProperty( "referenceCode" );
		assertNotNull( referenceCodeBinding );
		assertEquals( 1, referenceCodeBinding.getColumnSpan() );
		validateOrderReferenceCodeColumn( (Column) referenceCodeBinding.getColumnIterator().next() );

		final Property placedBinding = orderBinding.getProperty( "placed" );
		assertNotNull( placedBinding );
		assertEquals( 1, placedBinding.getColumnSpan() );
		validateOrderPlacedColumn( (Column) placedBinding.getColumnIterator().next() );

		final Property fulfilledBinding = orderBinding.getProperty( "fulfilled" );
		assertNotNull( fulfilledBinding );
		assertEquals( 1, fulfilledBinding.getColumnSpan() );
		validateOrderFulfilledColumn( (Column) fulfilledBinding.getColumnIterator().next() );

		final Property customerBinding = orderBinding.getProperty( "customer" );
		assertNotNull( customerBinding );
		assertEquals( 1, customerBinding.getColumnSpan() );
		validateOrderCustomerColumn( (Column) customerBinding.getColumnIterator().next() );
	}
