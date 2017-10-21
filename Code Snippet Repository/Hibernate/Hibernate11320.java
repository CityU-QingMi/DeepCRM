	@Test
	@Priority(9)
	public void initDataUpdateDetachedChanged() {
		final Author author = new Author( 2, "Author2" );
		final Book book = new Book( 2, "Book2", author );

		// Revision 1 - insert new entities.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.save( author );
			session.save( book );
		} );

		// Revision 2 - update detached with changes.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			book.setName( "Book2Updated" );
			session.update( book );
		} );
	}
