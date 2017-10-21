	@Test
	public void testSerializeDeserialize() throws IOException, ClassNotFoundException {
		l.add( action4 );
		l.add( action3 );
		l.add( action2 );
		l.add( action1 );
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream( baos );
		l.writeExternal( oos );
		// this OOS stream needs to be flushed...
		oos.flush();
		ByteArrayInputStream bin = new ByteArrayInputStream( baos.toByteArray() );
		ObjectInputStream ois = new ObjectInputStream( bin );
		l = new ExecutableList<NonSortedExecutableListTest.AnExecutable>( false );
		l.readExternal( ois );

		Assert.assertEquals( 4, l.size() );
		Assert.assertEquals( action4, l.get( 0 ) );
		Assert.assertEquals( action3, l.get( 1 ) );
		Assert.assertEquals( action2, l.get( 2 ) );
		Assert.assertEquals( action1, l.get( 3 ) );

		Assert.assertFalse( l.get( 0 ).afterDeserializeCalled );
		Assert.assertFalse( l.get( 1 ).afterDeserializeCalled );
		Assert.assertFalse( l.get( 2 ).afterDeserializeCalled );
		Assert.assertFalse( l.get( 3 ).afterDeserializeCalled );

		l.afterDeserialize( null );

		Assert.assertTrue( l.get( 0 ).afterDeserializeCalled );
		Assert.assertTrue( l.get( 1 ).afterDeserializeCalled );
		Assert.assertTrue( l.get( 2 ).afterDeserializeCalled );
		Assert.assertTrue( l.get( 3 ).afterDeserializeCalled );

		Assert.assertEquals( action4, l.get( 0 ) );
		Assert.assertEquals( action3, l.get( 1 ) );
		Assert.assertEquals( action2, l.get( 2 ) );
		Assert.assertEquals( action1, l.get( 3 ) );

		// sort after deserializing; it should still have no affect
		l.sort();
		Assert.assertEquals( action4, l.get( 0 ) );
		Assert.assertEquals( action3, l.get( 1 ) );
		Assert.assertEquals( action2, l.get( 2 ) );
		Assert.assertEquals( action1, l.get( 3 ) );
	}
