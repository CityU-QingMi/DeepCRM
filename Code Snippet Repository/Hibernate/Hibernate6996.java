	@Test
	public void testUniqueConstraintWithEmptyColumnName() {
		doInHibernate( this::sessionFactory, session -> {
			Customer customer1 = new Customer();
			customer1.customerId = "123";
			session.persist( customer1 );
		} );
		try {
			doInHibernate( this::sessionFactory, session -> {
				Customer customer1 = new Customer();
				customer1.customerId = "123";
				session.persist( customer1 );
			} );
			fail( "Should throw" );
		}
		catch ( PersistenceException e ) {
			assertEquals(
					ConstraintViolationException.class,
					e.getCause().getClass()
			);
		}
	}
