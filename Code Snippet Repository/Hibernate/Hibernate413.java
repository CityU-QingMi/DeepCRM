	@Test @TestForIssue( jiraKey = "")
	public void test_HHH10557() {

		Person _person = doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.setName( "John Doe" );
			entityManager.persist( person );
			person.getPhones().add( "123-456-7890" );
			person.getPhones().add( "123-456-0987" );
			return person;
		} );

		try {

			doInJPA( this::entityManagerFactory, entityManager -> {
				Long postId = _person.getId();
				Person person = entityManager.find( Person.class, postId );
				assertEquals( 2, person.getPhones().size() );
				person.getPhones().remove( 0 );
				person.setName( "Mr. John Doe" );
			} );


			doInJPA( this::entityManagerFactory, entityManager -> {
				Long postId = _person.getId();
				Person person = entityManager.find( Person.class, postId );
				assertEquals( 1, person.getPhones().size() );
			} );
		}
		catch (Exception e) {
			log.error( "Throws NullPointerException because the bag is not initialized by the @Loader" );
		}
	}
