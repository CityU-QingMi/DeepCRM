	@Test
	public void testCreateSequenceExportScripts() throws Exception {
		final String[] createStrings = getDialect().getCreateSequenceStrings( "REVISION_GENERATOR", 1, 1 );
		final String content = new String( Files.readAllBytes( createSchema.toPath() ) ).toLowerCase();
		for ( int i = 0; i < createStrings.length; ++i ) {
			if ( getDialect() instanceof Oracle8iDialect ) {
				assertTrue( content.contains( ( createStrings[ i ] + " ORDER" ).toLowerCase() ) );
			}
			else {
				assertTrue( content.contains( createStrings[ i ].toLowerCase() ) );
			}
		}
	}
