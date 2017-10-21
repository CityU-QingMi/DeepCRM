	@Test
	public void testLifecycle() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.id = 1L;
			person.getPhones().add( "123-456-7890" );
			person.getPhones().add( "456-000-1234" );
			entityManager.persist( person );
		} );
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = entityManager.find( Person.class, 1L );
			log.info( "Remove one element" );
			//tag::collections-value-type-collection-order-column-remove-example[]
			person.getPhones().remove( 0 );
			//end::collections-value-type-collection-order-column-remove-example[]
		} );
	}
