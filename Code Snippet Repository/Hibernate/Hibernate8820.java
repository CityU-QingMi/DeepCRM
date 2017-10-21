	private void assertSameTableUsed(Metadata metadata) {
		Collection inputs1Mapping = metadata.getCollectionBinding( Ptx.class.getName() + ".inputs1" );
		assertEquals( "ptx_input", inputs1Mapping.getCollectionTable().getName() );

		Collection inputs2Mapping = metadata.getCollectionBinding( Ptx.class.getName() + ".inputs2" );
		assertEquals( "ptx_input", inputs2Mapping.getCollectionTable().getName() );

		assertSame( inputs1Mapping.getCollectionTable(), inputs2Mapping.getCollectionTable() );

		// NOTE : here so that tester can more easily see the produced table. It is only dumped to stdout
		new SchemaExport().create( EnumSet.of( TargetType.STDOUT ), metadata );

		for ( int i = 0; i < inputs1Mapping.getCollectionTable().getColumnSpan(); i++ ) {
			final Column column = inputs1Mapping.getCollectionTable().getColumn( i );

			// this, coupled with JPA saying the 2 collections implicitly map to the same table,
			// is the crux of the problem: all columns are null, so we effectively can never
			// insert rows into it.
			assertFalse( column.isNullable() );
		}
	}
