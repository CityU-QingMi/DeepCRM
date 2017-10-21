	@Test
	public void testSerializedEquality() throws Exception {
		doTest( buildBasicKey( null ) );

		doTest( buildBasicKey( CacheableResultTransformer.create( null, null, new boolean[] { true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( null, new String[] { null }, new boolean[] { true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( null, new String[] { "a" }, new boolean[] { true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( null, null, new boolean[] { false, true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( null, new String[] { "a" }, new boolean[] { true, false } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( null, new String[] { "a", null }, new boolean[] { true, true } ) ) );
	}
