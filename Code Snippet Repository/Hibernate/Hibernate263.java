	@Test
	public void testDistinctAuthors() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-distinct-entity-query-example[]
			List<Person> authors = entityManager.createQuery(
				"select distinct p " +
				"from Person p " +
				"left join fetch p.books", Person.class)
			.getResultList();
			//end::hql-distinct-entity-query-example[]

			authors.forEach( author -> {
				log.infof( "Author %s wrote %d books",
				   author.getFirstName() + " " + author.getLastName(),
				   author.getBooks().size()
				);
			} );
		});
	}
