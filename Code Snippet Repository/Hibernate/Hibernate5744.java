	private void testNumericLiterals(EntityManager entityManager, String expectedSQL) {
		final CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		final CriteriaQuery<Tuple> query = cb.createQuery( Tuple.class );

		final Root<Book> entity = query.from( Book.class );
		query.where( cb.equal(
				entity.get( "id" ),
				cb.literal( 1 )
		) );

		query.multiselect(
				cb.literal( "abc" ),
				entity.get( "name" )
		);

		connectionProvider.clear();
		List<Tuple> tuples = entityManager.createQuery( query )
				.getResultList();
		assertEquals( 1, tuples.size() );

		assertNotNull( connectionProvider.getPreparedStatement(expectedSQL) );
	}
