	@Test
	public void testParseExceptions() {
		boolean b = ConfigurationHelper.getBoolean( "parse.error", props );
		assertFalse( "parse exception case - boolean", b );

		try {
			ConfigurationHelper.getInt( "parse.error", props, 20 );
			fail( "parse exception case - int" );
		}
		catch( NumberFormatException expected ) {
		}

		try {
			ConfigurationHelper.getInteger( "parse.error", props );
			fail( "parse exception case - Integer" );
		}
		catch( NumberFormatException expected ) {
		}
	}
