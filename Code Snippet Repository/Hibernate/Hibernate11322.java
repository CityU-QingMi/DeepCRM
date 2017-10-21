	@Test
	@Priority(7)
	public void initDataUpdateDetachedChangedAndUnchanged() {
		final Author author = new Author( 4, "Author4" );
		final Book book = new Book( 4, "Book4", author );

		// Revision 1 - insert new entities.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.save( author );
			session.save( book );
		} );

		// Revision 2 - update detached with changes.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			book.setName( "Book4Updated" );
			session.update( book );
		} );

		// Revision 3 - update detached with no changes.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.update( book );
		} );
	}
