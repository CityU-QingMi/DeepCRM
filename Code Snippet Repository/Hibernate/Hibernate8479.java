	@Test
	public void testHigherLevelIndexDefinition() throws Throwable {
		Table table = metadata().getDatabase().getDefaultNamespace().locateTable( Identifier.toIdentifier( "TA" ) );
		Iterator<Index> indexItr = table.getIndexIterator();
		boolean found = false;
		while ( indexItr.hasNext() ) {
			final Index index = indexItr.next();
			if ( "indx_a_name".equals( index.getName() ) ) {
				found = true;
				break;
			}
		}
		assertTrue("Unable to locate indx_a_name index", found);
	}
