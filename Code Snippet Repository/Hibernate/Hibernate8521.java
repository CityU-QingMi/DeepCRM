	@Test
	public void testCollectionWhere() throws Exception {
		Foo foo1 = new Foo();
		Foo foo2 = new Foo();
		Baz baz = new Baz();
		Foo[] arr = new Foo[10];
		arr[0] = foo1;
		arr[9] = foo2;

		Session s = openSession();
		s.beginTransaction();
		s.save( foo1 );
		s.save(foo2);
		baz.setFooArray( arr );
		s.save( baz );
		s.getTransaction().commit();
		s.close();

		final Session s2 = openSession();
		s2.beginTransaction();
		baz = (Baz) s2.load( Baz.class, baz.getCode() );
		assertTrue( baz.getFooArray().length == 1 );
		assertTrue( s2.createQuery( "from Baz baz join baz.fooArray foo" ).list().size()==1 );
		assertTrue( s2.createQuery( "from Foo foo" ).list().size()==2 );
		assertTrue( s2.createFilter( baz.getFooArray(), "" ).list().size() == 1 );
		//assertTrue( s.delete("from java.lang.Object o")==9 );
		doDelete( s2, "from Foo foo" );
		final String bazid = baz.getCode();
		s2.delete( baz );
		int rows = s2.doReturningWork(
				new AbstractReturningWork<Integer>() {
					@Override
					public Integer execute(Connection connection) throws SQLException {
						Statement st = connection.createStatement();
						return st.executeUpdate( "delete from FOO_ARRAY where id_='" + bazid + "' and i>=8" );
					}
				}
		);
		assertTrue( rows == 1 );
		s2.getTransaction().commit();
		s2.close();
	}
