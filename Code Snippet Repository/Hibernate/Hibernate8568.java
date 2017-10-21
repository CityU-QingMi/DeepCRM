	@Test
	public void testPSCache() throws Exception {
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		for ( int i=0; i<10; i++ ) s.save( new Foo() );
		Query q = s.createQuery("from Foo");
		q.setMaxResults(2);
		q.setFirstResult(5);
		assertTrue( q.list().size()==2 );
		q = s.createQuery("from Foo");
		assertTrue( q.list().size()==10 );
		assertTrue( q.list().size()==10 );
		q.setMaxResults(3);
		q.setFirstResult(3);
		assertTrue( q.list().size()==3 );
		q = s.createQuery("from Foo");
		assertTrue( q.list().size()==10 );
		txn.commit();
		s.close();

		s = openSession();
		txn = s.beginTransaction();
		q = s.createQuery("from Foo");
		assertTrue( q.list().size()==10 );
		q.setMaxResults(5);
		assertTrue( q.list().size()==5 );
		doDelete( s, "from Foo" );
		txn.commit();
		s.close();
	}
