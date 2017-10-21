	@Test
	public void testLiteralsInSelectClause() throws Exception {
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {
				final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
				CriteriaQuery<String> query = cb.createQuery( String.class );
				Root<Book> root = query.from( Book.class );
				ListJoin<Book, Author> authors = root.joinList( "authors" );

				query.where( cb.equal(
						root.get( "name" ),
						"Java Persistence with Hibernate"
				))
				.select( cb.literal( "( SELECT REPEAT('abc' || ' ', 10000000000 FROM MY_ENTITY )" ) );

				entityManager.createQuery( query ).getResultList();
			} );
		}
		catch ( Exception expected ) {
			assertEquals(
					QuerySyntaxException.class,
					expected.getCause().getClass()
			);
		}
	}
