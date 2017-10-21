	@Test
	public void testJoinTableSchemaName() {
		for ( Table table : metadata().collectTableMappings() ) {
			if ( TABLE_NAME.equals( table.getName() ) ) {
				Assert.assertEquals( SCHEMA_NAME, table.getSchema() );
				return;
			}
		}
		Assert.fail();
	}
