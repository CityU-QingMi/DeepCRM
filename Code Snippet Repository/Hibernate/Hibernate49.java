	@Override
	public void buildEntityManagerFactory() throws Exception {
		super.buildEntityManagerFactory();
		doInJPA( this::entityManagerFactory, entityManager -> {
			Person person = new Person();
			person.id = 1L;
			person.phones.add( "027-123-4567" );
			person.phones.add( "028-234-9876" );
			entityManager.persist( person );
		} );
	}
