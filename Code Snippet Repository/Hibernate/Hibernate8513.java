	@Test
	public void testOneToOneGenerator() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		X x = new X();
		Y y = new Y();
		x.setY(y);
		y.setTheX(x);
		x.getXxs().add( new X.XX(x) );
		x.getXxs().add( new X.XX(x) );
		Serializable id = s.save(y);
		assertEquals( id, s.save(x) );
		s.flush();
		assertTrue( s.contains(y) && s.contains(x) );
		s.getTransaction().commit();
		s.close();
		assertEquals( new Long(x.getId()), y.getId() );

		s = openSession();
		s.beginTransaction();
		x = new X();
		y = new Y();
		x.setY(y);
		y.setTheX(x);
		x.getXxs().add( new X.XX(x) );
		s.save(y);
		s.flush();
		assertTrue( s.contains(y) && s.contains(x) );
		s.getTransaction().commit();
		s.close();
		assertEquals( new Long(x.getId()), y.getId() );

		s = openSession();
		s.beginTransaction();
		x = new X();
		y = new Y();
		x.setY(y);
		y.setTheX(x);
		x.getXxs().add( new X.XX(x) );
		x.getXxs().add( new X.XX(x) );
		id = s.save(x);
		assertEquals( id, y.getId() );
		assertEquals( id, new Long( x.getId() ) );
		s.flush();
		assertTrue( s.contains(y) && s.contains(x) );
		doDelete( s, "from X x" );
		s.getTransaction().commit();
		s.close();
	}
