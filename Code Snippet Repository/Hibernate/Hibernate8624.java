	@Test
	public void testCollectionOnly() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Mono m = new Mono();
		Long id = (Long) s.save(m);
		t.commit();
		s.close();
		s = openSession();
		t = s.beginTransaction();
		s.update( m );
		s.flush();
		m.setAddress("foo bar");
		s.flush();
		s.delete(m);
		t.commit();
		s.close();
	}
