	@Test
	public void testHaltOnError() {
		try {
			new SchemaUpdate().setHaltOnError( true )
					.execute( EnumSet.of( TargetType.DATABASE ), metadata );
			fail( "Should halt on error!" );
		}
		catch ( Exception e ) {
			SchemaManagementException cause = (SchemaManagementException) e;
			assertEquals("Halting on error : Error executing DDL via JDBC Statement", cause.getMessage());
		}
	}
