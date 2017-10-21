	@Test
	public void testDialectNotFound() {
		Map properties = Collections.EMPTY_MAP;
		try {
			dialectFactory.buildDialect(
					properties,
					new DialectResolutionInfoSource() {
						@Override
						public DialectResolutionInfo getDialectResolutionInfo() {
							return TestingDialectResolutionInfo.forDatabaseInfo( "NoSuchDatabase", 666 );
						}
					}
			);
			fail();
		}
		catch ( HibernateException e ) {
			assertNull( e.getCause() );
		}
	}
