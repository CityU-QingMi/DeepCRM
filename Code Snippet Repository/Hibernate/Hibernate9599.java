	@Test
	@Override
	public void testExternalization() {
		// blobs of the same internal value are not really comparable
		String externalized = BlobTypeDescriptor.INSTANCE.toString( original );
		Blob consumed = BlobTypeDescriptor.INSTANCE.fromString( externalized );
		try {
			PrimitiveByteArrayTypeDescriptor.INSTANCE.areEqual(
					DataHelper.extractBytes( original.getBinaryStream() ),
					DataHelper.extractBytes( consumed.getBinaryStream() )
			);
		}
		catch ( SQLException e ) {
			fail( "SQLException accessing blob : " + e.getMessage() );
		}
	}
