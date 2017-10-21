	@Test
	@TestForIssue( jiraKey = "" )
	public void testJoinColumnConfiguredInXml() {
		PersistentClass pc = metadata().getEntityBinding( Son.class.getName() );
		Iterator iter = pc.getJoinIterator();
		Table table = ( ( Join ) iter.next() ).getTable();
		Iterator columnIter = table.getColumnIterator();
		boolean fooFound = false;
		boolean barFound = false;
		while ( columnIter.hasNext() ) {
			Column column = ( Column ) columnIter.next();
			if ( column.getName().equals( "foo" ) ) {
				fooFound = true;
			}
			if ( column.getName().equals( "bar" ) ) {
				barFound = true;
			}
		}
		assertTrue(
				"The mapping defines join columns which could not be found in the metadata.", fooFound && barFound
		);
	}
