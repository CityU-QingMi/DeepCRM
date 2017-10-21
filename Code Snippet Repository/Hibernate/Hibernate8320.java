	@Test
	public void testBatchOrdering() {
		doInHibernate( this::sessionFactory, session -> {
			Task task = new Task();
			task.addCategory(Category.A);
			session.persist( task );

			Task task1 = new Task();
			task1.addCategory(Category.A);
			session.persist( task1 );
		} );
	}
