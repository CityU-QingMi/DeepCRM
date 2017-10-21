	@Test
	public void testAdd() {
		Assert.assertEquals( 0, l.size() );
		l.add( action1 );
		Assert.assertEquals( action1, l.get( 0 ) );
		Assert.assertEquals( 1, l.size() );
		l.add( action2 );
		Assert.assertEquals( action2, l.get( 1 ) );
		l.add( action3 );
		Assert.assertEquals( action3, l.get( 2 ) );
		Assert.assertEquals( 3, l.size() );
	}
