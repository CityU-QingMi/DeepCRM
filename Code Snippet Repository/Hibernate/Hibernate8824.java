	protected void validateZipCode(Metadata metadata) {
		final PersistentClass zipCodeBinding = metadata.getEntityBinding( ZipCode.class.getName() );
		assertNotNull( zipCodeBinding );

		validateZipCodePrimaryTableName( zipCodeBinding.getTable().getQuotedName() );

		assertEquals( 1, zipCodeBinding.getIdentifier().getColumnSpan() );
		validateZipCodePrimaryKeyColumn( (Column) zipCodeBinding.getIdentifier().getColumnIterator().next() );

		final Property codeBinding = zipCodeBinding.getProperty( "code" );
		assertNotNull( codeBinding );
		assertEquals( 1, codeBinding.getColumnSpan() );
		validateZipCodeCodeColumn( (Column) codeBinding.getColumnIterator().next() );

		final Property cityBinding = zipCodeBinding.getProperty( "city" );
		assertNotNull( cityBinding );
		assertEquals( 1, cityBinding.getColumnSpan() );
		validateZipCodeCityColumn( (Column) cityBinding.getColumnIterator().next() );

		final Property stateBinding = zipCodeBinding.getProperty( "state" );
		assertNotNull( stateBinding );
		assertEquals( 1, stateBinding.getColumnSpan() );
		validateZipCodeStateColumn( (Column) stateBinding.getColumnIterator().next() );
	}
