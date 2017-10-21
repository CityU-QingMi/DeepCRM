	@Before
	public void init() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Book book = new Book();
			book.name = "Java Persistence with Hibernate";

			Author author1 = new Author();
			author1.name = "Christian Bauer";

			Author author2 = new Author();
			author1.name = "Gavin Ling";

			book.authors.add( author1 );
			book.authors.add( author2 );
			entityManager.persist( book );
		} );
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {
				entityManager
						.createNativeQuery(
								"SELECT REPEAT('abc' || ' ', 1000000000000) FROM MY_ENTITY" )
						.getSingleResult();
			} );
			fail( "Should have thrown exception!" );
		}
		catch ( Exception expected ) {
			assertEquals(
					SQLGrammarException.class,
					expected.getCause().getClass()
			);
		}
	}
