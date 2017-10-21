	@Test
	public void testJoin() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Foo foo = new Foo();
		foo.setJoinedProp("foo");
		s.save( foo );
		s.flush();
		foo.setJoinedProp("bar");
		s.flush();
		String fid = foo.getKey();
		s.delete( foo );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Foo foo2 = new Foo();
		foo2.setJoinedProp("foo");
		s.save(foo2);
		s.createQuery( "select foo.id from Foo foo where foo.joinedProp = 'foo'" ).list();
		assertNull( s.get(Foo.class, fid) );
		s.delete(foo2);
		s.getTransaction().commit();
		s.close();

	}
