	@Test
	public void testBinaryType() {
		final byte[] original = new byte[] { 1, 2, 3, 4 };
		final byte[] copy = new byte[] { 1, 2, 3, 4 };
		final byte[] different = new byte[] { 4, 3, 2, 1 };

		runBasicTests( BinaryType.INSTANCE, original, copy, different );
		runBasicTests( ImageType.INSTANCE, original, copy, different );
		runBasicTests( MaterializedBlobType.INSTANCE, original, copy, different );
	}
