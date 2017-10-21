	@Test
	public void testSort() {
		l.add( action4 );
		l.add( action3 );
		l.add( action2 );
		l.add( action1 );
		// sort should have no affect
		l.sort();
		Assert.assertEquals( action4, l.get( 0 ) );
		Assert.assertEquals( action3, l.get( 1 ) );
		Assert.assertEquals( action2, l.get( 2 ) );
		Assert.assertEquals( action1, l.get( 3 ) );
	}
