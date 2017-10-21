	@Test
	public void testLifecycle() {
		doInHibernate( this::sessionFactory, session -> {
			Person person = new Person();
			person.id = 1L;
			session.persist( person );

			//tag::collections-comma-delimited-collection-lifecycle-example[]
			person.phones.add( "027-123-4567" );
			person.phones.add( "028-234-9876" );
			session.flush();
			person.getPhones().remove( 0 );
			//end::collections-comma-delimited-collection-lifecycle-example[]
		} );
	}
