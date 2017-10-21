	public void testSimpleSelectWithLimitAndOffset() throws Exception {
		// just checking correctness of param binding code...
		Session session = openSession();
		Transaction t = session.beginTransaction();
		session.createQuery( "from Animal" )
				.setFirstResult( 2 )
				.setMaxResults( 1 )
				.list();
		t.commit();
		session.close();
	}
