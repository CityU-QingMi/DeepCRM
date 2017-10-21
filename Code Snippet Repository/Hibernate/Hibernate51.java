	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			log.info( "Clear element collection and add element" );
			//tag::collections-value-type-collection-lifecycle-example[]
			person.getPhones().clear();
			person.getPhones().add( "123-456-7890" );
			person.getPhones().add( "456-000-1234" );
			//end::collections-value-type-collection-lifecycle-example[]
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			log.info( "Remove one element" );
			//tag::collections-value-type-collection-remove-example[]
			person.getPhones().remove( 0 );
			//end::collections-value-type-collection-remove-example[]
		} );
	}
