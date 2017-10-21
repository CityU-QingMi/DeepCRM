	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person( 1L );
			entityManager.persist( person );
			//tag::collections-bidirectional-bag-lifecycle-example[]
			person.addPhone( new Phone( 1L, "landline", "028-234-9876" ) );
			person.addPhone( new Phone( 2L, "mobile", "072-122-9876" ) );
			entityManager.flush();
			person.removePhone( person.getPhones().get( 0 ) );
			//end::collections-bidirectional-bag-lifecycle-example[]
		} );
	}
