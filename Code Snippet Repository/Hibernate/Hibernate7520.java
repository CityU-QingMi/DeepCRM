	@Test
	public void testFixForHHH7842DoesNotBreakOldBehavior() {
		doInHibernate( this::sessionFactory, session -> {

			assertOnlyOneSelect( session.createCriteria( Cat.class )
					.setFetchMode( "kittens", FetchMode.JOIN )
					.setResultTransformer( CriteriaSpecification.DISTINCT_ROOT_ENTITY ) );

		} );
	}
