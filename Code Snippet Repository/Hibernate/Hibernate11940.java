	private void compare(Map<Integer, Boolean> dbexpected, List list) {
		int cnt = 0;
		for ( Map.Entry<Integer, Boolean> entry : dbexpected.entrySet() ) {
			if ( entry.getValue() ) {
				cnt++;
				if ( !findInList( entry.getKey(), (List<GeomEntity>) list ) ) {
					fail( String.format( "Expected object with id= %d, but not found in result", entry.getKey() ) );
				}
			}
		}
		assertEquals( cnt, list.size() );
		LOG.info( String.format( "Found %d objects within testsuite-suite polygon.", cnt ) );
	}
