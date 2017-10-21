	@Test
	public void testTransactionProtection() {
		Session session = sessionFactory().getCurrentSession();
		try {
			session.createQuery( "from Silly" );
			fail( "method other than beginTransaction() allowed" );
		}
		catch ( HibernateException e ) {
			// ok
		}
	}
