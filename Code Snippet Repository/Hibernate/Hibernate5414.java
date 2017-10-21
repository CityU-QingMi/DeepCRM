	private void testDetermination(
			final String databaseName,
			final int majorVersion,
			final int minorVersion,
			Class expected,
			DialectResolver resolver) {
		dialectFactory.setDialectResolver( resolver );
		Dialect resolved = dialectFactory.buildDialect(
				new Properties(),
				new DialectResolutionInfoSource() {
					@Override
					public DialectResolutionInfo getDialectResolutionInfo() {
						return TestingDialectResolutionInfo.forDatabaseInfo( databaseName, majorVersion, minorVersion );
					}
				}
		);
		assertEquals( expected, resolved.getClass() );
	}
