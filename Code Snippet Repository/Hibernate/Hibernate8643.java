	@Test
	public void testObjectType() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Parent g = new Parent();
		Foo foo = new Foo();
		g.setAny(foo);
		s.save(g);
		s.save(foo);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		g = (Parent) s.load( Parent.class, new Long( g.getId() ) );
		assertTrue( g.getAny()!=null && g.getAny() instanceof FooProxy );
		s.delete( g.getAny() );
		s.delete(g);
		s.getTransaction().commit();
		s.close();
	}
