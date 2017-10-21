	@Test
	public void testSharedColumn() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		C1 c1 = new C1();
		C2 c2 = new C2();
		c1.setC2(c2);
		c2.setC1(c1);
		s.save(c1); s.save(c2);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		List list = s.createQuery( "from B" ).list();
		assertTrue( list.size()==2 );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c1 = (C1) s.createQuery("from C1").uniqueResult();
		c2 = (C2) s.createQuery("from C2").uniqueResult();
		assertTrue( c1.getC2()==c2 );
		assertTrue( c2.getC1()==c1 );
		assertTrue( c1.getC2s().contains(c2) );
		assertTrue( c2.getC1s().contains(c1) );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		c1 = (C1) s.get( A.class, c1.getId() );
		c2 = (C2) s.get( A.class, c2.getId() );
		assertTrue( c1.getC2()==c2 );
		assertTrue( c2.getC1()==c1 );
		assertTrue( c1.getC2s().contains(c2) );
		assertTrue( c2.getC1s().contains(c1) );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.delete(c1); s.delete(c2);
		t.commit();
		s.close();

	}
