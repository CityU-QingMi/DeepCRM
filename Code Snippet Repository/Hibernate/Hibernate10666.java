	@Test
	public void testAbstractTableExistence() {
		for ( Table table : metadata().collectTableMappings() ) {
			if ( "AbstractEntity_AUD".equals( table.getName() ) ) {
				Assert.assertFalse( table.isPhysicalTable() );
				return;
			}
		}
		Assert.fail();
	}
