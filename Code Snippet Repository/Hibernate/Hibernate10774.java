	@Test
	@Priority(10)
	public void initData() throws Exception {
		// Revision 1 | Create ticket with comments
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Ticket ticket = new Ticket( 1, "data-t1" );
			final Comment comment = new Comment( 1, "Initial comment-t1" );
			ticket.addComment( comment );
			entityManager.persist( comment );
			entityManager.persist( ticket );
		} );

		// Revision 2 | Create ticket without comments
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Ticket ticket = new Ticket( 2, "data-t2" );
			entityManager.persist( ticket );
		} );

		// Revision 3 | Update ticket with comments
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Ticket ticket = entityManager.find( Ticket.class, 1 );
			ticket.setData( "data-changed-t1" );
			entityManager.merge( ticket );
		} );

		// Revision 4 | Update ticket without comments
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Ticket ticket = entityManager.find( Ticket.class, 2 );
			ticket.setData( "data-changed-t2" );
			entityManager.merge( ticket );
		} );

		// Revision 5 | Update ticket and comment
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Ticket ticket = entityManager.find( Ticket.class, 1 );
			ticket.setData( "data-changed-twice" );
			ticket.getComments().get( 0 ).setText( "comment-modified" );
			ticket.getComments().forEach( entityManager::merge );
			entityManager.merge( ticket );
		} );

		// Revision 6 | Update ticket and comment collection
		TransactionUtil.doInJPA( this::entityManagerFactory, entityManager -> {
			final Ticket ticket = entityManager.find( Ticket.class, 1 );
			final Comment comment = new Comment( 2, "Comment2" );
			ticket.addComment( comment );
			entityManager.merge( comment );
			entityManager.merge( ticket );
		} );
	}
