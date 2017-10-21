	@Test
	public void testCriteriaParameters() throws Exception {
		doInJPA( this::entityManagerFactory, entityManager -> {
			final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<String> query = cb.createQuery( String.class );
			Root<Book> root = query.from( Book.class );
			ListJoin<Book, Author> authors = root.joinList( "authors" );

			query.where( cb.equal(
					root.get( "name" ),
					"( SELECT REPEAT('abc' || ' ', 10000000000 FROM MY_ENTITY )"
			), cb.equal( authors.index(), 0 ) )
					.select( authors.get( "name" ) );

			connectionProvider.clear();
			entityManager.createQuery( query ).getResultList();
			assertEquals(
					1,
					connectionProvider.getPreparedStatements().size()
			);
			assertNotNull( connectionProvider.getPreparedStatement(
					"select authors1_.name as col_0_0_ from Book criteriali0_ inner join Author authors1_ on criteriali0_.id=authors1_.book_id where criteriali0_.name=? and authors1_.index_id=0" )
			);
		} );
	}
