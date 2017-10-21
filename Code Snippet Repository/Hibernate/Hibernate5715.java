	@Test
	public void getResultWithStringPropertyDerivedPath() {
		doTest(
				new JoinBuilder() {
					@Override
					public Join<EmbeddedType, ManyToOneType> buildJoinToManyToOneType(Join<Entity, EmbeddedType> source) {
						return source.join( "manyToOneType", JoinType.LEFT );
					}
				}
		);
	}
