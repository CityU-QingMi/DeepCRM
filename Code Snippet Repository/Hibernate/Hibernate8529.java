	@Test
	public void loadFoo() {
		Session s = openSession();
		s.beginTransaction();
		FooProxy foo = new Foo();
		s.save( foo );
		s.getTransaction().commit();
		s.close();

		final String id = ( (Foo) foo ).key;

		s = openSession();
		s.beginTransaction();
		foo = (FooProxy) s.load( Foo.class, id );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( foo );
		s.getTransaction().commit();
		s.close();
	}
