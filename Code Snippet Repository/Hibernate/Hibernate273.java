	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::entity-inheritance-polymorphism-persist-example[]
			Book book = new Book();
			book.setId( 1L );
			book.setAuthor( "Vlad Mihalcea" );
			book.setTitle( "High-Performance Java Persistence" );
			entityManager.persist( book );

			Blog blog = new Blog();
			blog.setId( 1L );
			blog.setSite( "vladmihalcea.com" );
			entityManager.persist( blog );
			//end::entity-inheritance-polymorphism-persist-example[]
		} );

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::entity-inheritance-polymorphism-fetch-example[]
			List<DomainModelEntity> accounts = entityManager
			.createQuery(
				"select e " +
				"from org.hibernate.userguide.inheritance.polymorphism.DomainModelEntity e" )
			.getResultList();

			assertEquals(1, accounts.size());
			assertTrue( accounts.get( 0 ) instanceof Book );
			//end::entity-inheritance-polymorphism-fetch-example[]
		} );
	}
