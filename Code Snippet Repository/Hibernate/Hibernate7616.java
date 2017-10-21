	@Test
	public void testPessimisticLockWithUnionThenFollowOnLocking() {

		final Session session = openSession();
		session.beginTransaction();


		sqlStatementInterceptor.getSqlQueries().clear();

		List<Vehicle> vehicles = session.createQuery( "select v from Vehicle v" )
			.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE ) )
			.getResultList();

		assertEquals( 3, vehicles.size() );
		assertEquals( 4, sqlStatementInterceptor.getSqlQueries().size() );

		session.getTransaction().commit();
		session.close();
	}
