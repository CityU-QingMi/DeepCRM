	@Test
	@TestForIssue( jiraKey = "" )
	@RequiresDialect( value = MySQL5Dialect.class )
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person1 = new Person();
			person1.setId( 1L );
			person1.setName( "John" );

			Person person2 = new Person();
			person2.setId( 2L );
			person2.setName( "Doe" );

			Person person3 = new Person();
			person3.setId( 3L );
			person3.setName( "J" );

			Person person4 = new Person();
			person4.setId( 4L );
			person4.setName( "D" );

			entityManager.persist( person1 );
			entityManager.persist( person2 );
			entityManager.persist( person3 );
			entityManager.persist( person4 );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			List<Person> persons = entityManager.createQuery(
				"SELECT p " +
				"FROM Person p " +
				"WHERE p.id IN (:ids) " +
				"ORDER BY FIELD(id, :ids) ", Person.class)
			.setParameter( "ids" , Arrays.asList(3L, 1L, 2L))
			.getResultList();

			assertEquals(3, persons.size());
			int index = 0;
			assertEquals( Long.valueOf( 3L ), persons.get( index++ ).getId() );
			assertEquals( Long.valueOf( 1L ), persons.get( index++ ).getId() );
			assertEquals( Long.valueOf( 2L ), persons.get( index++ ).getId() );
		} );
	}
