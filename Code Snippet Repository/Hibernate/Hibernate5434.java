	@Test
	public void testGetSpaces() {
		l.add( action1 );
		Set<Serializable> ss = l.getQuerySpaces();
		Assert.assertEquals( 1, ss.size() );
		Assert.assertTrue( ss.contains( "a" ) );
		l.add( action2 );
		l.add( action3 );
		l.add( action4 );
		Set<Serializable> ss2 = l.getQuerySpaces();
		Assert.assertEquals( 4, ss2.size() );
		Assert.assertTrue( ss2.contains( "a" ) );
		Assert.assertTrue( ss2.contains( "b" ) );
		Assert.assertTrue( ss2.contains( "c" ) );
		Assert.assertTrue( ss2.contains( "d" ) );
		Assert.assertTrue( ss == ss2 ); // same Set (cached)
		// now remove action4
		l.remove( 3 );
		ss2 = l.getQuerySpaces();
		Assert.assertTrue( ss == ss2 ); // same Set (action4 has no spaces)
		Assert.assertEquals( 4, ss2.size() );
		l.remove( 2 );
		ss2 = l.getQuerySpaces();
		Assert.assertTrue( ss != ss2 ); // Different Set because it has been rebuilt. This would be incorrect if
		// Set.clear() was used
	}
