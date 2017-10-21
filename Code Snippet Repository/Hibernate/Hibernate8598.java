	@Test
	public void testMultiLevelCascade() throws Exception {
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		Detail detail = new Detail();
		SubDetail subdetail = new SubDetail();
		Master m = new Master();
		Master m0 = new Master();
		Serializable m0id = s.save(m0);
		m0.addDetail(detail);
		detail.setMaster(m0);
		m.getMoreDetails().add(detail);
		detail.setSubDetails( new HashSet() );
		detail.getSubDetails().add(subdetail);
		Serializable mid = s.save(m);
		txn.commit();
		s.close();

		s = openSession();
		txn = s.beginTransaction();
		m = (Master) s.load( Master.class, mid );
		assertTrue( ( (Detail) m.getMoreDetails().iterator().next() ).getSubDetails().size()!=0 );
		s.delete(m);
		assertTrue( s.createQuery( "from SubDetail" ).list().size()==0 );
		assertTrue( s.createQuery( "from Detail d" ).list().size()==0 );
		s.delete( s.load(Master.class, m0id) );
		txn.commit();
		s.close();
	}
