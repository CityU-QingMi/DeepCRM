	@Test
	public void testRevEntityTableCreation() {
		for ( Table table : metadata().collectTableMappings() ) {
			if ( "REVCHANGES".equals( table.getName() ) ) {
				assert table.getColumnSpan() == 2;
				assert table.getColumn( new Column( "REV" ) ) != null;
				assert table.getColumn( new Column( "ENTITYNAME" ) ) != null;
				return;
			}
		}
		assert false;
	}
