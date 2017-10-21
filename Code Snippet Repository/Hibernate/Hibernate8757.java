	@Test
	public void testCriteria() {
		Session session = openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria( Door.class );
		criteria.setLockMode( LockMode.PESSIMISTIC_WRITE );
		criteria.setFirstResult( 2 );
		criteria.setMaxResults( 2 );
		@SuppressWarnings("unchecked") List<Door> results = criteria.list();
		assertEquals( 2, results.size() );
		for ( Door door : results ) {
			assertEquals( LockMode.PESSIMISTIC_WRITE, session.getCurrentLockMode( door ) );
		}
		session.getTransaction().commit();
		session.close();
	}
