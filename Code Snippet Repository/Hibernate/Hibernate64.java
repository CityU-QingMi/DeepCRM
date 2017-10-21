	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::collections-customizing-ordered-list-ordinal-persist-example[]
			Person person = new Person( 1L );
			entityManager.persist( person );
			person.addPhone( new Phone( 1L, "landline", "028-234-9876" ) );
			person.addPhone( new Phone( 2L, "mobile", "072-122-9876" ) );
			//end::collections-customizing-ordered-list-ordinal-persist-example[]
		} );
	}
