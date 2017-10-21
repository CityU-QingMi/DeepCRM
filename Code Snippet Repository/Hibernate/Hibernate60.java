	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.id = 1L;
			//tag::collections-embeddable-type-collection-lifecycle-example[]
			person.getPhones().add( new Phone( "landline", "028-234-9876" ) );
			person.getPhones().add( new Phone( "mobile", "072-122-9876" ) );
			//end::collections-embeddable-type-collection-lifecycle-example[]
			entityManager.persist( person );
		} );
	}
