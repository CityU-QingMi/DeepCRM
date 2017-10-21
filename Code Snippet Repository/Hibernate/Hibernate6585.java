	@Override
	protected void prepareTest() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			Mammal mammal = new Mammal();
			mammal.setName( "unimportant" );
			session.persist( mammal );

			Human human = new Human();
			human.setName( "unimportant" );
			session.persist( human );

			Human human1 = new Human();
			human1.setName( "unimportant_1" );
			session.persist( human1 );
		} );
	}
