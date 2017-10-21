	@Test
	public void test() {
		//tag::envers-revisionlog-RevisionEntity-persist-example[]
		CurrentUser.INSTANCE.logIn( "Vlad Mihalcea" );

		doInJPA( this::entityManagerFactory, entityManager -> {
			Customer customer = new Customer();
			customer.setId( 1L );
			customer.setFirstName( "John" );
			customer.setLastName( "Doe" );

			entityManager.persist( customer );
		} );

		CurrentUser.INSTANCE.logOut();
		//end::envers-revisionlog-RevisionEntity-persist-example[]
	}
