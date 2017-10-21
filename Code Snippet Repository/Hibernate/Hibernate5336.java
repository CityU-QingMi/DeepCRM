	@Test
	public void testSerializedEqualityResultTransformer() throws Exception {
		// settings are lazily initialized when calling transformTuple(),
		// so they have not been initialized for the following test
		// (it *should* be initialized before creating a QueryKey)
		doResultTransformerTest( new AliasToBeanResultTransformer( AClass.class ), false );

		// initialize settings for the next test
		AliasToBeanResultTransformer transformer = new AliasToBeanResultTransformer( AClass.class );
		transformer.transformTuple(
				new Object[] { "abc", "def" },
				new String[] { "propAccessedByField", "propAccessedByMethod" }
		);
		doResultTransformerTest( transformer, false );

		doResultTransformerTest( AliasToEntityMapResultTransformer.INSTANCE, true );
		doResultTransformerTest( DistinctResultTransformer.INSTANCE, true );
		doResultTransformerTest( DistinctRootEntityResultTransformer.INSTANCE, true );
		doResultTransformerTest( PassThroughResultTransformer.INSTANCE, true );
		doResultTransformerTest( RootEntityResultTransformer.INSTANCE, true );
		doResultTransformerTest( ToListResultTransformer.INSTANCE, true );
	}
