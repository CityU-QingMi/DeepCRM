	@Test
	public void testLiteralsInWhereClause() throws Exception {

		doInJPA( this::entityManagerFactory, entityManager -> {

			final CriteriaBuilder cb = entityManager.getCriteriaBuilder();

			final CriteriaQuery<Tuple> query = cb.createQuery( Tuple.class );

			final Root<Book> entity = query.from( Book.class );
			query.where( cb.equal(
					entity.get( "name" ),
					cb.literal(
							"( SELECT REPEAT('abc' || ' ', 10000000000 FROM MY_ENTITY )" )
			) );

			query.multiselect(
					cb.literal( "abc" ),
					entity.get( "name" )
			);

			connectionProvider.clear();
			List<Tuple> tuples = entityManager.createQuery( query )
					.getResultList();

			assertEquals(
					1,
					connectionProvider.getPreparedStatements().size()
			);
			assertNotNull( connectionProvider.getPreparedStatement(
					"select 'abc' as col_0_0_, criteriali0_.name as col_1_0_ from Book criteriali0_ where criteriali0_.name=?" ) );
			assertTrue( tuples.isEmpty() );
		} );
	}
