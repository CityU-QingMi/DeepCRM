	@Test
	public void test() {
		doInHibernate( this::sessionFactory, session -> {
			CorporateUser user = new CorporateUser();
			user.setUserName( "Vlad" );
			session.persist( user );

			user.getEmailAddresses().add( "vlad@hibernate.info" );
			user.getEmailAddresses().add( "vlad@hibernate.net" );
		} );
		doInHibernate( this::sessionFactory, session -> {
			List<CorporateUser> users = session.createQuery(
				"select u from CorporateUser u where u.emailAddresses = :address", CorporateUser.class )
			.setParameter( "address", new Array(), ArrayType.INSTANCE )
			.getResultList();

			assertTrue( users.isEmpty() );
		} );
	}
