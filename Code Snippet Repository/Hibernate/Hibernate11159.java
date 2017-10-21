	@Test
	@Priority(10)
	public void initData() {
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Person person = new Person( 1, "Chris" );
			final Document document = new Document( 1, "DL" );
			final PersonDocument pd = new PersonDocument( person, document );
			entityManager.persist( person );
			entityManager.persist( document );
			entityManager.persist( pd );
		} );

		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Person person = entityManager.find( Person.class, 1 );
			final Document document = new Document( 2, "Passport" );
			final PersonDocument pd = new PersonDocument( person, document );
			entityManager.persist( document );
			entityManager.persist( pd );
		} );

		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Person person = entityManager.find( Person.class, 1 );
			final Document document = entityManager.find( Document.class, 1 );
			final PersonDocument pd = entityManager
					.createQuery( "FROM PersonDocument WHERE id.person.id = :person AND id.document.id = :document", PersonDocument.class )
					.setParameter( "person", person.getId() )
					.setParameter( "document", document.getId() )
					.getSingleResult();

			entityManager.remove( pd );
			entityManager.remove( document );
		} );
	}
