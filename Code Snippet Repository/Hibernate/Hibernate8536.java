	@Test
	public void testIterators() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		for ( int i=0; i<10; i++ ) {
			Qux q = new Qux();
			Object qid = s.save(q);
			assertTrue("not null", qid!=null);
		}
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Iterator iter = s.createQuery( "from Qux q where q.stuff is null" ).iterate();
		int count=0;
		while ( iter.hasNext() ) {
			Qux q = (Qux) iter.next();
			q.setStuff("foo");
			if (count==0 || count==5) iter.remove();
			count++;
		}
		assertTrue("iterate", count==10);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		assertEquals( 8, doDelete( s, "from Qux q where q.stuff=?", "foo", StandardBasicTypes.STRING ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		iter = s.createQuery( "from Qux q" ).iterate();
		assertTrue( "empty iterator", !iter.hasNext() );
		s.getTransaction().commit();
		s.close();
	}
