	@Test
	public void testAutoFlush() throws Exception {
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		FooProxy foo = new Foo();
		s.save(foo);
		assertTrue( "autoflush create", s.createQuery( "from Foo foo" ).list().size()==1 );
		foo.setChar( 'X' );
		assertTrue( "autoflush update", s.createQuery( "from Foo foo where foo.char='X'" ).list().size()==1 );
		txn.commit();
		s.close();

		s = openSession();
		txn = s.beginTransaction();
		foo = (FooProxy) s.load( Foo.class, foo.getKey() );
		//s.update( new Foo(), foo.getKey() );
		//assertTrue( s.find("from Foo foo where not foo.char='X'").size()==1, "autoflush update" );
		if ( !(getDialect() instanceof MySQLDialect) && !(getDialect() instanceof HSQLDialect) && !(getDialect() instanceof PointbaseDialect) )  {
			foo.setBytes( "osama".getBytes() );
			assertTrue( "autoflush collection update",
					s.createQuery( "from Foo foo where 111 in elements(foo.bytes)" ).list().size()==1 );
			foo.getBytes()[0] = 69;
			assertTrue( "autoflush collection update",
					s.createQuery( "from Foo foo where 69 in elements(foo.bytes)" ).list()
							.size()==1 );
		}
		s.delete(foo);
		assertTrue( "autoflush delete", s.createQuery( "from Foo foo" ).list().size()==0 );
		txn.commit();
		s.close();
	}
