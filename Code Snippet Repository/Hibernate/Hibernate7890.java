	private void checkTestOrderByResult(Object result,
										Zoo zooExpected,
										Set<Zoo> zoosUnordered) {
		assertTrue( result instanceof Object[] );
		Object[] resultArray = ( Object[] ) result;
		assertEquals( 2,  resultArray.length );
		Hibernate.initialize( ( ( Address ) resultArray[ 1 ] ).getStateProvince() );
		if ( zooExpected == null ) {
			Zoo zooResult = new Zoo();
			zooResult.setName( ( String ) resultArray[ 0 ] );
			zooResult.setAddress( ( Address ) resultArray[ 1 ] );
			assertTrue( zoosUnordered.remove( zooResult ) );
		}
		else {
			assertEquals( zooExpected.getName(), ( ( Object[] ) result )[ 0 ] );
			assertEquals( zooExpected.getAddress(), ( ( Object[] ) result )[ 1 ] );
		}
	}
