	@Test
	@Priority(10)
	public void initDataUpdateDetachedUnchanged() {
		final Author author = new Author( 1, "Author1" );
		final Book book = new Book( 1, "Book1", author );

		// Revision 1 - insert new entities.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.save( author );
			session.save( book );
		} );

		// Revision 2 - update detached with no changes.
		TransactionUtil.doInHibernate( this::sessionFactory, session -> {
			session.update( book );
		} );
	}
