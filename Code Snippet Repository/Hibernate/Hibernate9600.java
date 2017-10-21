	@Test
	public void testConversionFromString() {
		assertEquals( toLocale( "de", null, null ), LocaleTypeDescriptor.INSTANCE.fromString( "de" ) );
		assertEquals( toLocale( "de", "DE", null ), LocaleTypeDescriptor.INSTANCE.fromString( "de_DE" ) );
		assertEquals( toLocale( null, "DE", null ), LocaleTypeDescriptor.INSTANCE.fromString( "_DE" ) );
		assertEquals( toLocale( null, null, "ch123" ), LocaleTypeDescriptor.INSTANCE.fromString( "__ch123" ) );
		assertEquals( toLocale( null, "DE", "ch123" ), LocaleTypeDescriptor.INSTANCE.fromString( "_DE_ch123" ) );
		assertEquals( toLocale( "de", null, "ch123" ), LocaleTypeDescriptor.INSTANCE.fromString( "de__ch123" ) );
		assertEquals( toLocale( "de", "DE", "ch123" ), LocaleTypeDescriptor.INSTANCE.fromString( "de_DE_ch123" ) );
		assertEquals( toLocale( "", "", "" ), LocaleTypeDescriptor.INSTANCE.fromString( "" ) );
		assertEquals( Locale.ROOT, LocaleTypeDescriptor.INSTANCE.fromString( "" ) );
	}
