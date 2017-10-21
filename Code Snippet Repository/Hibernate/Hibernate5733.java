	@Test
	public void testLiteralHandlingMode() throws Exception {
		doInJPA( this::entityManagerFactory, entityManager -> {
			final CriteriaBuilder cb = entityManager.getCriteriaBuilder();

			final CriteriaQuery<Tuple> query = cb.createQuery( Tuple.class );

			final Root<Book> entity = query.from( Book.class );
			query.where(
				cb.and(
					cb.equal(
							entity.get( "id" ),
							cb.literal( 1 )
					),
					cb.equal(
							entity.get( "name" ),
							cb.literal( bookName() )
					)
				)
			);

			query.multiselect(
					cb.literal( "abc" ),
					entity.get( "name" )
			);

			connectionProvider.clear();
			List<Tuple> tuples = entityManager.createQuery( query )
					.getResultList();
			assertEquals( 1, tuples.size() );

			assertNotNull( connectionProvider.getPreparedStatement( expectedSQL() ) );
		} );
	}
