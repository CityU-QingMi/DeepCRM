	@Test
	public void testCreateUpdate() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Foo foo = new Foo();
		s.save(foo);
		foo.setString("dirty");
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Foo foo2 = new Foo();
		s.load( foo2, foo.getKey() );
		// There is an interbase bug that causes null integers to return as 0, also numeric precision is <= 15
		assertTrue( "create-update", foo.equalsFoo(foo2) );
		//System.out.println( s.print(foo2) );
		s.delete(foo2);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		foo = new Foo();
		s.save(foo);
		foo.setString("dirty");
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.load(foo2, foo.getKey());
		// There is an interbase bug that causes null integers to return as 0, also numeric precision is <= 15
		assertTrue( "create-update", foo.equalsFoo(foo2) );
		//System.out.println( s.print(foo2) );
		s.delete(foo2);
		s.getTransaction().commit();
		s.close();
	}
