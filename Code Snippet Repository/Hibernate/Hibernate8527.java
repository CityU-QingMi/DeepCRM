	@Test
	public void testCreate() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Foo foo = new Foo();
		s.save(foo);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Foo foo2 = new Foo();
		s.load( foo2, foo.getKey() );
		// There is an interbase bug that causes null integers to return as 0, also numeric precision is <= 15
		assertTrue( "create", foo.equalsFoo( foo2 ) );
		s.delete(foo2);
		s.getTransaction().commit();
		s.close();
	}
