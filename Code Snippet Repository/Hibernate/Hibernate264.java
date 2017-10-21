	@Test
	public void testDistinctAuthorsWithoutPassThrough() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::hql-distinct-entity-query-hint-example[]
			List<Person> authors = entityManager.createQuery(
				"select distinct p " +
				"from Person p " +
				"left join fetch p.books", Person.class)
			.setHint( QueryHints.HINT_PASS_DISTINCT_THROUGH, false )
			.getResultList();
			//end::hql-distinct-entity-query-hint-example[]

			authors.forEach( author -> {
				log.infof( "Author %s wrote %d books",
				   author.getFirstName() + " " + author.getLastName(),
				   author.getBooks().size()
				);
			} );
		});
	}
