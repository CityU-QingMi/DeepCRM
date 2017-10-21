	@Test
	public void testLimit() throws Exception {
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		for ( int i=0; i<10; i++ ) s.save( new Foo() );
		Iterator iter = s.createQuery("from Foo foo")
			.setMaxResults(4)
			.setFirstResult(2)
			.iterate();
		int count=0;
		while ( iter.hasNext() ) {
		    iter.next();
			count++;
		}
		assertEquals(4, count);
		iter = s.createQuery("select distinct foo from Foo foo")
			.setMaxResults(2)
			.setFirstResult(2)
			.list()
			.iterator();
		count=0;
		while ( iter.hasNext() ) {
			iter.next();
			count++;
		}
		assertTrue(count==2);
		iter = s.createQuery("select distinct foo from Foo foo")
		.setMaxResults(3)
		.list()
		.iterator();
		count=0;
		while ( iter.hasNext() ) {
			iter.next();
			count++;
		}
		assertTrue(count==3);
		assertEquals( 10, doDelete( s, "from Foo foo" ) );
		txn.commit();
		s.close();
	}
