	@Test
	public void testNonLazyBidirectional() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Single sin = new Single();
		sin.setId("asdfds");
		sin.setString("adsa asdfasd");
		Several sev = new Several();
		sev.setId("asdfasdfasd");
		sev.setString("asd ddd");
		sin.getSeveral().add(sev);
		sev.setSingle(sin);
		s.save(sin);
		t.commit();
		s.close();
		s = openSession();
		t = s.beginTransaction();
		sin = (Single) s.load( Single.class, sin );
		t.commit();
		s.close();
		s = openSession();
		t = s.beginTransaction();
		sev = (Several) s.load( Several.class, sev );
		t.commit();
		s.close();
		s = openSession();
		t = s.beginTransaction();
		s.createQuery( "from Several" ).list();
		t.commit();
		s.close();
		s = openSession();
		t = s.beginTransaction();
		for ( Object entity : s.createQuery( "from Single" ).list() ) {
			s.delete( entity );
		}
		t.commit();
		s.close();
	}
