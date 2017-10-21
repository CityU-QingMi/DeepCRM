	@Test
	public void testObjectType() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		GlarchProxy g = new Glarch();
		Foo foo = new Foo();
		g.setAny( foo );
		Serializable gid = s.save( g );
		s.save(foo);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		g = (GlarchProxy) s.load(Glarch.class, gid);
		assertTrue( g.getAny()!=null && g.getAny() instanceof FooProxy );
		s.delete( g.getAny() );
		s.delete( g );
		s.getTransaction().commit();
		s.close();
	}
