	@Test
	public void testSaveDelete() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Foo f = new Foo();
		s.save(f);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( s.load( Foo.class, f.getKey() ) );
		s.getTransaction().commit();
		s.close();
	}
