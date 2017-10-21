	@Test
	@SuppressWarnings("")
	public void getResultWithMetamodelDerivedPath() {
		doTest(
				new JoinBuilder() {
					@Override
					public Join<EmbeddedType, ManyToOneType> buildJoinToManyToOneType(Join<Entity, EmbeddedType> source) {
						final SingularAttribute<EmbeddedType, ManyToOneType> attr =
								(SingularAttribute<EmbeddedType, ManyToOneType>) entityManagerFactory().getMetamodel()
										.managedType( EmbeddedType.class )
										.getDeclaredSingularAttribute( "manyToOneType" );
						return source.join( attr, JoinType.LEFT );
					}
				}
		);
	}
