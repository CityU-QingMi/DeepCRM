	@Test
	@SuppressWarnings( {""})
	public void testBooleanType() {
		final Boolean original = Boolean.TRUE;
		final Boolean copy = new Boolean( true );
		final Boolean different = Boolean.FALSE;

		runBasicTests( BooleanType.INSTANCE, original, copy, different );
		runBasicTests( NumericBooleanType.INSTANCE, original, copy, different );
		runBasicTests( YesNoType.INSTANCE, original, copy, different );
		runBasicTests( TrueFalseType.INSTANCE, original, copy, different );
	}
