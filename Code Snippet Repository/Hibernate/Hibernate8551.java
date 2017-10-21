	@Test
	public void testFindLoad() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		FooProxy foo = new Foo();
		s.save(foo);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		foo = (FooProxy) s.createQuery( "from Foo foo" ).list().get(0);
		FooProxy foo2 = (FooProxy) s.load( Foo.class, foo.getKey() );
		assertTrue( "find returns same object as load", foo == foo2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		foo2 = (FooProxy) s.load( Foo.class, foo.getKey() );
		foo = (FooProxy) s.createQuery( "from Foo foo" ).list().get(0);
		assertTrue( "find returns same object as load", foo == foo2 );
		doDelete( s, "from Foo foo" );
		s.getTransaction().commit();
		s.close();
	}
