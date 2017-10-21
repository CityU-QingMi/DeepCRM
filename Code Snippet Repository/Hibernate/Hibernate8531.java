	@Test
	public void testPolymorphism() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Bar bar = new Bar();
		s.save(bar);
		bar.setBarString("bar bar");
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		FooProxy foo = (FooProxy) s.load( Foo.class, bar.getKey() );
		assertTrue( "polymorphic", foo instanceof BarProxy );
		assertTrue( "subclass property", ( (BarProxy) foo ).getBarString().equals( bar.getBarString() ) );
		//System.out.println( s.print(foo) );
		s.delete(foo);
		s.getTransaction().commit();
		s.close();
	}
