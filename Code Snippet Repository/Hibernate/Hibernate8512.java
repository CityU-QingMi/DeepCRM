	@Test
	public void testEmptyCollection() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Serializable id = s.save( new Baz() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Baz baz = (Baz) s.load(Baz.class, id);
		Set foos = baz.getFooSet();
		assertTrue( foos.size() == 0 );
		Foo foo = new Foo();
		foos.add( foo );
		s.save(foo);
		s.flush();
		s.delete(foo);
		s.delete(baz);
		s.getTransaction().commit();
		s.close();
	}
