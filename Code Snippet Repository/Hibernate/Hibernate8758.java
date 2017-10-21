	public void testNativeSql() {
		Session session = openSession();
		session.beginTransaction();
		SQLQuery qry = session.createSQLQuery( "select * from door" );
		qry.addRoot( "door", Door.class );
		qry.getLockOptions().setLockMode( LockMode.PESSIMISTIC_WRITE );
		qry.setFirstResult( 2 );
		qry.setMaxResults( 2 );
		@SuppressWarnings("unchecked") List results = qry.list();
		assertEquals( 2, results.size() );
		for ( Object door : results ) {
			assertEquals( LockMode.PESSIMISTIC_WRITE, session.getCurrentLockMode( door ) );
		}
		session.getTransaction().commit();
		session.close();
	}
