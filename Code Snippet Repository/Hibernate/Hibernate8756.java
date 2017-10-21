	@Test
	public void testHql() {
		Session session = openSession();
		session.beginTransaction();
		Query qry = session.createQuery( "from Door" );
		qry.getLockOptions().setLockMode( LockMode.PESSIMISTIC_WRITE );
		qry.setFirstResult( 2 );
		qry.setMaxResults( 2 );
		@SuppressWarnings("unchecked") List<Door> results = qry.list();
		assertEquals( 2, results.size() );
		for ( Door door : results ) {
			assertEquals( LockMode.PESSIMISTIC_WRITE, session.getCurrentLockMode( door ) );
		}
		session.getTransaction().commit();
		session.close();
	}
