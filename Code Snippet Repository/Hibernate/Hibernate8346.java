	@Test
	public void testStatefulIntercept() {
		final StatefulInterceptor statefulInterceptor = new StatefulInterceptor();
		Session s = openSession( statefulInterceptor );
		statefulInterceptor.setSession(s);

		Transaction t = s.beginTransaction();
		User u = new User("Gavin", "nivag");
		s.persist(u);
		u.setPassword("vagni");
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		List logs = s.createCriteria(Log.class).list();
		assertEquals( 2, logs.size() );
		s.delete(u);
		s.createQuery( "delete from Log" ).executeUpdate();
		t.commit();
		s.close();
	}
