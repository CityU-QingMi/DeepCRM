	public void testStringLiteral2() {
		final EntityManager entityManager = getOrCreateEntityManager();
		try {
			final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			final CriteriaQuery<Tuple> criteriaQuery = builder.createQuery( Tuple.class );
			criteriaQuery.from( MyEntity.class );
			criteriaQuery.multiselect( builder.equal( builder.literal( 1 ), builder.literal( 2 ) ) );

			final TypedQuery<Tuple> typedQuery = entityManager.createQuery( criteriaQuery );

			final List<Tuple> results = typedQuery.getResultList();

			assertThat( results.size(), is( 1 ) );
			assertThat( results.get( 0 ).getElements().size(), is( 1 ) );
			assertThat( results.get( 0 ).get( 0 ), is( false ) );
		}
		finally {
			entityManager.close();
		}
	}
