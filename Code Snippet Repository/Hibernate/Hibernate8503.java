	@Test
	public void testUpdate() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Foo foo = new Foo();
		s.save( foo );
		s.getTransaction().commit();
		s.close();

		foo = (Foo) SerializationHelper.deserialize( SerializationHelper.serialize(foo) );

		s = openSession();
		s.beginTransaction();
		FooProxy foo2 = (FooProxy) s.load( Foo.class, foo.getKey() );
		foo2.setString("dirty");
		foo2.setBoolean( new Boolean( false ) );
		foo2.setBytes( new byte[] {1, 2, 3} );
		foo2.setDate( null );
		foo2.setShort( new Short( "69" ) );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		foo2.setString( "dirty again" );
		s.update(foo2);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		foo2.setString( "dirty again 2" );
		s.update( foo2 );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Foo foo3 = new Foo();
		s.load( foo3, foo.getKey() );
		// There is an interbase bug that causes null integers to return as 0, also numeric precision is <= 15
		assertTrue( "update", foo2.equalsFoo(foo3) );
		s.delete( foo3 );
		doDelete( s, "from Glarch" );
		s.getTransaction().commit();
		s.close();
	}
