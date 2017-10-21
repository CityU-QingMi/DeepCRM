	@Test
	public void testSerializedEqualityWithTupleSubsetResultTransfprmer() throws Exception {
		doTestWithTupleSubsetResultTransformer(
				new AliasToBeanResultTransformer( AClass.class ),
				new String[] { "propAccessedByField", "propAccessedByMethod" }
		);
		doTestWithTupleSubsetResultTransformer( AliasToEntityMapResultTransformer.INSTANCE, new String[] { "a", "b" } );
		doTestWithTupleSubsetResultTransformer( DistinctRootEntityResultTransformer.INSTANCE, new String[] { "a", "b" } );
		doTestWithTupleSubsetResultTransformer( PassThroughResultTransformer.INSTANCE, new String[] { "a", "b" } );
		doTestWithTupleSubsetResultTransformer( RootEntityResultTransformer.INSTANCE, new String[] { "a", "b" } );
		// The following are not TupleSubsetResultTransformers:
		// DistinctResultTransformer.INSTANCE
		// ToListResultTransformer.INSTANCE
	}
