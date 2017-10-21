	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.setId( 1L );
			person.setName( "Vlad Mihalcea" );

			person.addArticle(
				new Article(
					"High-Performance JDBC",
					"Connection Management, Statement Caching, Batch Updates"
				)
			);
			person.addArticle(
				new Article(
					"High-Performance Hibernate",
					"Associations, Lazy fetching, Concurrency Control, Second-level Caching"
				)
			);
			entityManager.persist( person );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::collections-customizing-ordered-by-sql-clause-fetching-example[]
			Person person = entityManager.find( Person.class, 1L );
			assertEquals(
				"High-Performance Hibernate",
				person.getArticles().get( 0 ).getName()
			);
			//end::collections-customizing-ordered-by-sql-clause-fetching-example[]
		} );
	}
