	@Override
	protected void validateCustomerHqAddressComponent(Component component) {
		assertEquals( 3, component.getColumnSpan() );
		Iterator<Selectable> selectables = component.getColumnIterator();
		int pass = 1;
		while ( selectables.hasNext() ) {
			final Column column = assertTyping( Column.class, selectables.next() );
			if ( pass == 1 ) {
				assertEquals( "line1", column.getQuotedName() );
			}
			else if ( pass == 2 ) {
				assertEquals( "line2", column.getQuotedName() );
			}
			else if ( pass == 3 ) {
				assertEquals( "zipCode", column.getQuotedName() );
			}
			pass++;
		}
	}
