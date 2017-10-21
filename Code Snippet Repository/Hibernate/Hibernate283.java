	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::locking-optimistic-version-timestamp-source-persist-example[]
			Person person = new Person();
			person.setId( 1L );
			person.setFirstName( "John" );
			person.setLastName( "Doe" );
			assertNull( person.getVersion() );

			entityManager.persist( person );
			assertNotNull( person.getVersion() );
			//end::locking-optimistic-version-timestamp-source-persist-example[]
		} );
	}
