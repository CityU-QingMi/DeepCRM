	@Test
	public void testSortedSetDefinitionInHbmXml() {
		final PersistentClass entityMapping = metadata().getEntityBinding( Search.class.getName() );

		final Property sortedSetProperty = entityMapping.getProperty( "searchResults" );
		final Collection sortedSetMapping = assertTyping( Collection.class, sortedSetProperty.getValue()  );
		assertTrue( "SortedSet mapping not interpreted as sortable", sortedSetMapping.isSorted() );

		final Property sortedMapProperty = entityMapping.getProperty( "tokens" );
		final Collection sortedMapMapping = assertTyping( Collection.class, sortedMapProperty.getValue()  );
		assertTrue( "SortedMap mapping not interpreted as sortable", sortedMapMapping.isSorted() );
	}
