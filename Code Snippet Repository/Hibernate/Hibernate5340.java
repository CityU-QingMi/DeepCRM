	public void doTestWithTupleSubsetResultTransformerNullAliases(TupleSubsetResultTransformer transformer) throws Exception {
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] { true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] { true, true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] { true, true, true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] { false, true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] { true, false } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] { false, true, true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] {true, false, true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] {true, true, false } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] {false, false, true } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] {false, true, false } ) ) );
		doTest( buildBasicKey( CacheableResultTransformer.create( transformer, null, new boolean[] {false, false, true } ) ) );
	}
