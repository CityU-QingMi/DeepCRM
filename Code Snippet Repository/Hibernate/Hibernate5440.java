	@Test
	public void testRemoveLastN() {
		l.add( action1 );
		l.add( action2 );
		l.add( action3 );
		l.removeLastN( 0 );
		Assert.assertEquals( 3, l.size() );
		l.removeLastN( 2 );
		Assert.assertEquals( 1, l.size() );
		Assert.assertEquals( action1, l.get( 0 ) );
	}
