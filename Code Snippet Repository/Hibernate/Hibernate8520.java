	@Test
	public void testAfterDelete() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Foo foo = new Foo();
		s.save(foo);
		s.flush();
		s.delete(foo);
		s.save(foo);
		s.delete(foo);
		s.getTransaction().commit();
		s.close();
	}
