	@Test
	public void testForCertain() throws Exception {
		Glarch g = new Glarch();
		Glarch g2 = new Glarch();
		List set = new ArrayList();
		set.add("foo");
		g2.setStrings(set);
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Serializable gid = s.save(g);
		Serializable g2id = s.save(g2);
		t.commit();
		assertTrue( g.getVersion()==0 );
		assertTrue( g2.getVersion()==0 );
		s.close();

		s = openSession();
		t = s.beginTransaction();
		g = (Glarch) s.get(Glarch.class, gid);
		g2 = (Glarch) s.get(Glarch.class, g2id);
		assertTrue( g2.getStrings().size()==1 );
		s.delete(g);
		s.delete(g2);
		t.commit();
		s.close();
	}
