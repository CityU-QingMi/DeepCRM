	@Test
	public void testJoinedIterables() {
		List<Iterable<Integer>> listOfIterables = new ArrayList<Iterable<Integer>>(  );

		List<Integer> twoElementList = Arrays.asList( 0, 1 );
		listOfIterables.add( twoElementList );

		List<Integer> emptyList = new ArrayList<Integer>(  );
		listOfIterables.add( emptyList );

		List<Integer> oneElementList = Arrays.asList( 2 );
		listOfIterables.add( oneElementList );

		List<Integer> threeElementList = Arrays.asList( 3, 4, 5 );
		listOfIterables.add( threeElementList );

		JoinedIterable<Integer> joinedIterable = new JoinedIterable<Integer>( listOfIterables );

		int i = 0;
		for ( Integer val : joinedIterable ) {
			assertEquals( Integer.valueOf( i ), val );
			i++;
		}
	}
