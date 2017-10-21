	@Test
	public void testVeto() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Vetoer v = new Vetoer();
		s.save(v);
		s.save(v);
		s.getTransaction().commit();
		s.close();
		s = openSession();
		s.beginTransaction();
		s.update( v );
		s.update( v );
		s.delete( v );
		s.delete( v );
		s.getTransaction().commit();
		s.close();
	}
