	@Test
	public void testCurrentStateOfEntity1() {
		final SortedSetEntity entity1 = getEntityManager().find( SortedSetEntity.class, 1 );

		assertEquals( "sortedEntity1", entity1.getData() );
		assertEquals( Integer.valueOf( 1 ), entity1.getId() );

		final SortedSet<StrTestEntity> sortedSet = entity1.getSortedSet();
		assertEquals( StrTestEntityComparator.class, sortedSet.comparator().getClass() );
		assertEquals( 4, sortedSet.size() );
		final Iterator<StrTestEntity> iterator = sortedSet.iterator();
		checkStrTestEntity( iterator.next(), id2, "aaa" );
		checkStrTestEntity( iterator.next(), id4, "aac" );
		checkStrTestEntity( iterator.next(), id3, "aba" );
		checkStrTestEntity( iterator.next(), id1, "abc" );

		final SortedMap<StrTestEntity, String> sortedMap = entity1.getSortedMap();
		assertEquals( StrTestEntityComparator.class, sortedMap.comparator().getClass() );
		assertEquals( 4, sortedMap.size() );
		Iterator<Map.Entry<StrTestEntity, String>> mapIterator = sortedMap.entrySet().iterator();
		checkStrTestEntity( mapIterator.next().getKey(), id2, "aaa" );
		checkStrTestEntity( mapIterator.next().getKey(), id4, "aac" );
		checkStrTestEntity( mapIterator.next().getKey(), id3, "aba" );
		checkStrTestEntity( mapIterator.next().getKey(), id1, "abc" );

		mapIterator = sortedMap.entrySet().iterator();
		assertEquals( mapIterator.next().getValue(), "aaa" );
		assertEquals( mapIterator.next().getValue(), "aac" );
		assertEquals( mapIterator.next().getValue(), "aba" );
		assertEquals( mapIterator.next().getValue(), "abc" );
	}
