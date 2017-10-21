	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::associations-one-to-many-bidirectional-lifecycle-example[]
			Person person = new Person();
			Phone phone1 = new Phone( "123-456-7890" );
			Phone phone2 = new Phone( "321-654-0987" );

			person.addPhone( phone1 );
			person.addPhone( phone2 );
			entityManager.persist( person );
			entityManager.flush();

			person.removePhone( phone1 );
			//end::associations-one-to-many-bidirectional-lifecycle-example[]
		} );
	}
