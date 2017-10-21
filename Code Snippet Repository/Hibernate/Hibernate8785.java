	@Test
	@TestForIssue( jiraKey = "" )
	public void testAliasOrdering() {
		Iterator<Table> tables = metadata().collectTableMappings().iterator();
		Table table1 = null;
		Table table2 = null;
		while ( tables.hasNext() ) {
			Table table = tables.next();
			if ( table.getName().equals( "Table1" ) ) {
				table1 = table;
			}
			else if ( table.getName().equals( "Table2" ) ) {
				table2 = table;
			}
		}
		
		assertTrue( table1.getUniqueInteger() < table2.getUniqueInteger() );
	}
