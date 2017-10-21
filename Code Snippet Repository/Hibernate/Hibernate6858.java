	@Test
	@TestForIssue( jiraKey = "" )
	public void testJoinColumnConfiguredInXml() {
		PersistentClass pc = metadata().getEntityBinding( Model.class.getName() );
		Table table = pc.getRootTable();
		Iterator iter = table.getColumnIterator();
		boolean joinColumnFound = false;
		while(iter.hasNext()) {
			Column column = (Column) iter.next();
			if(column.getName().equals( "model_manufacturer_join" )) {
				joinColumnFound = true;
			}
		}
		assertTrue( "The mapping defines a joing column which could not be found in the metadata.", joinColumnFound );
	}
