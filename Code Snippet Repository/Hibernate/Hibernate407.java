	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Book book = new Book();
			book.setId( 0L );
			book.setTitle( "Hibernate in Action" );
			book.setPrice( 49.99d );

			entityManager.persist( book );
		} );
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {
				//tag::schema-generation-database-checks-persist-example[]
				Book book = new Book();
				book.setId( 1L );
				book.setPrice( 49.99d );
				book.setTitle( "High-Performance Java Persistence" );
				book.setIsbn( "11-11-2016" );

				entityManager.persist( book );
				//end::schema-generation-database-checks-persist-example[]
			} );
			fail("Should fail because the ISBN is not of the right length!");
		}
		catch ( PersistenceException e ) {
			assertEquals( ConstraintViolationException.class, e.getCause().getCause().getClass() );
		}
		try {
			doInJPA( this::entityManagerFactory, entityManager -> {
				Person person = new Person();
				person.setId( 1L );
				person.setName( "John Doe" );
				person.setCode( 0L );

				entityManager.persist( person );
			} );
			fail("Should fail because the code is 0!");
		}
		catch ( PersistenceException e ) {
			assertEquals( ConstraintViolationException.class, e.getCause().getCause().getClass() );
		}
	}
