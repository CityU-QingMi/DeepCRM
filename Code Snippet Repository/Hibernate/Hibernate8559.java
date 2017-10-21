	@Test
	public void testLoadAfterDelete() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Foo foo = new Foo();
		Serializable id = s.save(foo);
		s.flush();
		s.delete(foo);
		boolean err=false;
		try {
			s.load(Foo.class, id);
		}
		catch (ObjectNotFoundException ode) {
			err=true;
		}
		assertTrue(err);
		s.flush();
		err=false;
		try {
			( (FooProxy) s.load(Foo.class, id) ).getBool();
		}
		catch (ObjectNotFoundException onfe) {
			err=true;
		}
		assertTrue(err);
		id = FumTest.fumKey( "abc" ); //yuck!!
		Fo fo = Fo.newFo( (FumCompositeID) id );
		s.save(fo);
		s.flush();
		s.delete(fo);
		err=false;
		try {
			s.load(Fo.class, id);
		}
		catch (ObjectNotFoundException ode) {
			err=true;
		}
		assertTrue(err);
		s.flush();
		err=false;
		try {
			s.load(Fo.class, id);
		}
		catch (ObjectNotFoundException onfe) {
			err=true;
		}
		assertTrue(err);
		s.getTransaction().commit();
		s.close();
	}
