	@Test
	public void testComplicatedQuery() throws Exception {
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		Foo foo = new Foo();
		Serializable id = s.save(foo);
		assertTrue( id != null );
		Qux q = new Qux("q");
		foo.getDependent().setQux(q);
		s.save( q );
		q.getFoo().setString( "foo2" );
		//s.flush();
		//s.connection().commit();
		assertTrue(
				s.createQuery( "from Foo foo where foo.dependent.qux.foo.string = 'foo2'" ).iterate().hasNext()
		);
		s.delete( foo );
		txn.commit();
		s.close();
	}
