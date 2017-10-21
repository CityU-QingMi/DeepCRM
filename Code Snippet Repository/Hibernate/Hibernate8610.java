	@Test
	public void testNotNullDiscriminator() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Up up = new Up();
		up.setId1("foo");
		up.setId2(123l);
		Down down = new Down();
		down.setId1("foo");
		down.setId2(321l);
		down.setValue(12312312l);
		s.save(up);
		s.save(down);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		List list = s.createQuery( "from Up up order by up.id2 asc" ).list();
		assertTrue( list.size()==2 );
		assertFalse( list.get(0) instanceof Down );
		assertTrue( list.get(1) instanceof Down );
		list = s.createQuery( "from Down down" ).list();
		assertTrue( list.size()==1 );
		assertTrue( list.get(0) instanceof Down );
		//list = s.find("from Up down where down.class = Down");
		assertTrue( list.size()==1 );
		assertTrue( list.get(0) instanceof Down );
		for ( Object entity : s.createQuery( "from Up" ).list() ) {
			s.delete( entity );
		}
		t.commit();
		s.close();

	}
