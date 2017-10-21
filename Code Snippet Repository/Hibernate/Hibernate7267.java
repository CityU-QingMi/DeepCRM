	@Test
	public void testProxyClassLoader() {

		Session s = openSession();
		Transaction t = s.beginTransaction();
		IPerson p = new Person();
		p.setId( 1 );
		s.persist( p );
		s.flush();
		s.clear();

		Object lp = s.load( Person.class, p.getId() );

		Assert.assertTrue( "Loaded entity is not an instance of the proxy interface", IPerson.class.isInstance( lp ) );
		Assert.assertFalse( "Proxy class was not created", Person.class.isInstance( lp ) );

		s.delete( lp );
		t.commit();
		s.close();
	}
