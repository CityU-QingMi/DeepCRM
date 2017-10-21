	@Test
	public void testCachedCollection() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		baz.setDefaults();
		s.save(baz);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz = (Baz) s.load( Baz.class, baz.getCode() );
		( (FooComponent) baz.getTopComponents().get(0) ).setCount(99);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz = (Baz) s.load( Baz.class, baz.getCode() );
		assertTrue( ((FooComponent) baz.getTopComponents().get( 0 )).getCount() == 99 );
		s.delete( baz );
		s.getTransaction().commit();
		s.close();
	}
