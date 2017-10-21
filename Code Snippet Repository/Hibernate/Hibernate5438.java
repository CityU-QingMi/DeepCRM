	@Test
	public void testClear() {
		Assert.assertTrue( l.isEmpty() );
		l.add( action1 );
		Assert.assertFalse( l.isEmpty() );
		l.add( action2 );
		l.clear();
		Assert.assertTrue( l.isEmpty() );
		Assert.assertEquals( 0, l.size() );
	}
