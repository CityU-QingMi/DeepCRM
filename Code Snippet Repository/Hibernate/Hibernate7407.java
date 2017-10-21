	private void testSelectBeforeUpdate() {
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			final Person john = new Person( 1, "John", new Address() );
			session.save( john );

			final Person mary = new Person( 2, "Mary", null );
			session.save( mary );
		} );

		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			final Person john = session.find( Person.class, 1 );
			i.reset();
			john.setAddress( null );
			session.flush();
			assertEquals( 0, i.getCalls() );

			i.reset();
			final Person mary = session.find( Person.class, 2 );
			mary.setAddress( new Address() );
			session.flush();
			assertEquals( 0, i.getCalls() );
		} );

		final Person john = TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			return session.get( Person.class, 1 );
		} );

		final Person mary = TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			return session.get( Person.class, 2 );
		} );

		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			i.reset();
			john.setAddress( null );
			session.update( john );
			session.flush();
			assertEquals( 0, i.getCalls() );

			i.reset();
			mary.setAddress( new Address() );
			session.update( mary );
			session.flush();
			assertEquals( 0, i.getCalls() );

		} );
	}
