	@Test
	public void testSubclassMap() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		B b = new B();
		s.save(b);
		Map map = new HashMap();
		map.put("3", new Integer(1) );
		b.setMap(map);
		s.flush();
		s.delete(b);
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		map = new HashMap();
		map.put("3", new Integer(1) );
		b = new B();
		b.setMap(map);
		s.save(b);
		s.flush();
		s.delete(b);
		t.commit();
		s.close();
	}
