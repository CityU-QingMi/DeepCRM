	private static void runDialectTest(
			String productName,
			int majorVersion,
			int minorVersion,
			Class<? extends Dialect> expectedDialect) {
		TestingDialectResolutionInfo info = TestingDialectResolutionInfo.forDatabaseInfo( productName, majorVersion, minorVersion );

		Dialect dialect = StandardDialectResolver.INSTANCE.resolveDialect( info );

		StringBuilder builder = new StringBuilder( productName ).append( " " )
				.append( majorVersion );
		if ( minorVersion > 0 ) {
			builder.append( "." ).append( minorVersion );
		}
		String dbms = builder.toString();

		assertNotNull( "Dialect for " + dbms + " should not be null", dialect );
		assertTrue( "Dialect for " + dbms + " should be " 
				+ expectedDialect.getSimpleName(),
						expectedDialect.isInstance( dialect ) );
	}
