	@Test
	public void testAllAuthors() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			List<Person> authors = entityManager.createQuery(
				"select p " +
				"from Person p " +
				"left join fetch p.books", Person.class)
			.getResultList();

			authors.forEach( author -> {
				log.infof( "Author %s wrote %d books",
				   author.getFirstName() + " " + author.getLastName(),
				   author.getBooks().size()
				);
			} );
		});
	}
