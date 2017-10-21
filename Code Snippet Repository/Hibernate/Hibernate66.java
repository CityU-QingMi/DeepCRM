	@Test
	public void test() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( 1L );
			person.getPhones().add( new Phone( 1L, "landline", "028-234-9876" ) );
			person.getPhones().add( new Phone( 2L, "mobile", "072-122-9876" ) );
			entityManager.persist( person );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::collections-custom-collection-example[]
			Person person = entityManager.find( Person.class, 1L );
			Queue<Phone> phones = person.getPhones();
			Phone head = phones.peek();
			assertSame(head, phones.poll());
			assertEquals( 1, phones.size() );
			//end::collections-custom-collection-example[]
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			person.getPhones().clear();
		} );
	}
