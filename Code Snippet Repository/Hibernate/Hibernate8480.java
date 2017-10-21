	@Test
	public void testGetSave() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		assertNull( s.get( D.class, Long.valueOf(1) ) );
		D d = new D();
		d.setId( Long.valueOf(1) );
		s.save(d);
		s.flush();
		assertNotNull( s.get( D.class, Long.valueOf(1) ) );
		s.delete(d);
		s.flush();
		t.commit();
		s.close();
	}
