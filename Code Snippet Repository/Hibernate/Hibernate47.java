	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( 1L );
			String[] phones = new String[2];
			phones[0] = "028-234-9876";
			phones[1] = "072-122-9876";
			person.setPhones( phones );
			entityManager.persist( person );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			String[] phones = new String[1];
			phones[0] = "072-122-9876";
			person.setPhones( phones );
		} );
	}
