	@Test
	public void testStringType() {
		final String original = "abc";
		final String copy = new String( original.toCharArray() );
		final String different = "xyz";

		runBasicTests( StringType.INSTANCE, original, copy, different );
		runBasicTests( TextType.INSTANCE, original, copy, different );
		runBasicTests( MaterializedClobType.INSTANCE, original, copy, different );
	}
