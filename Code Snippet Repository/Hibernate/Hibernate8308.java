	private void createTestData() {
		doInHibernate( this::sessionFactory, session -> {
			Category category7;
			session.persist( new Category( 1 ) );
			session.persist( new Category( 2 ) );
			session.persist( new Category( 3 ) );
			session.persist( new Category( 4 ) );
			session.persist( new Category( 5 ) );
			session.persist( new Category( 6 ) );
			session.persist( category7 = new Category( 7 ) );
			session.persist( new Post( 8, category7 ) );
		} );
	}
