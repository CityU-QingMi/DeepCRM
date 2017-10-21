	private void testDetermination(
			DialectResolver resolver,
			String databaseName,
			int version,
			Class dialectClass) throws SQLException {
		Dialect dialect = resolver.resolveDialect( TestingDialectResolutionInfo.forDatabaseInfo( databaseName, version ) );
		if ( dialectClass == null ) {
			assertEquals( null, dialect );
		}
		else {
			assertEquals( dialectClass, dialect.getClass() );
		}
	}
