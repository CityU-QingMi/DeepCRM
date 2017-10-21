	@Test
	@Priority(8)
	public void initDataUpdateDetachedUnchangedAndChanged() {
		final Author author = new Author( 3, "Author3" );
		final Book book = new Book( 3, "Book3", author );

		// Revision 1 - insert new entities.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.save( author );
			session.save( book );
		} );

		// Revision 2 - update detached with no changes.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.update( book );
		} );

		// Revision 3 - update detached with changes.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			book.setName( "Book3Updated" );
			session.update( book );
		} );
	}
