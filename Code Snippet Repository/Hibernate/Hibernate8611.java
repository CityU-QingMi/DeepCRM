	@Test
	public void testSelfManyToOne() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Master m = new Master();
		m.setOtherMaster(m);
		s.save(m);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		Iterator i = s.createQuery( "from Master" ).iterate();
		m = (Master) i.next();
		assertTrue( m.getOtherMaster()==m );
		if ( getDialect() instanceof HSQLDialect || getDialect() instanceof MySQLDialect ) {
			m.setOtherMaster(null);
			s.flush();
		}
		s.delete(m);
		t.commit();
		s.close();
	}
