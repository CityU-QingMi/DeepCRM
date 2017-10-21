	@Override
	protected void cleanupTestData() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			session.createQuery( "delete Address" ).executeUpdate();
			session.createQuery( "delete Person" ).executeUpdate();
			session.createQuery( "delete SpecialPerson" ).executeUpdate();
			session.createQuery( "delete AnotherPerson" ).executeUpdate();
			session.createQuery( "delete Office" ).executeUpdate();
			session.createQuery( "delete President" ).executeUpdate();
		} );
	}
