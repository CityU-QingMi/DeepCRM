	@Test
	public void testCallback() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Qux q = new Qux("0");
		s.save(q);
		q.setChild( new Qux( "1" ) );
		s.save( q.getChild() );
		Qux q2 = new Qux("2");
		q2.setChild( q.getChild() );
		Qux q3 = new Qux("3");
		q.getChild().setChild(q3);
		s.save( q3 );
		Qux q4 = new Qux("4");
		q4.setChild( q3 );
		s.save(q4);
		s.save( q2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		List l = s.createQuery( "from Qux" ).list();
		assertTrue( "", l.size() == 5 );
		s.delete( l.get( 0 ) );
		s.delete( l.get( 1 ) );
		s.delete( l.get( 2 ) );
		s.delete( l.get(3) );
		s.delete( l.get(4) );
		s.getTransaction().commit();
		s.close();
	}
