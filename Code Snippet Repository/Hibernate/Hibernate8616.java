	@Test
	public void testCascading() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Detail d1 = new Detail();
		Detail d2 = new Detail();
		d2.setI(22);
		Master m = new Master();
		Master m0 = new Master();
		Serializable m0id = s.save(m0);
		m0.addDetail(d1); m0.addDetail(d2);
		d1.setMaster(m0); d2.setMaster(m0);
		m.getMoreDetails().add(d1);
		m.getMoreDetails().add(d2);
		Serializable mid = s.save(m);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		m = (Master) s.load(Master.class, mid);
		assertTrue( "cascade save", m.getMoreDetails().size()==2 );
		assertTrue( "cascade save", ( (Detail) m.getMoreDetails().iterator().next() ).getMaster().getDetails().size()==2 );
		s.delete( m );
		s.delete( s.load( Master.class, m0id ) );
		s.getTransaction().commit();
		s.close();
	}
