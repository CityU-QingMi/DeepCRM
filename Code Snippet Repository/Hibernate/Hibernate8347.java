	@Test
	public void testInitiateIntercept() {
		final String injectedString = "******";
		final InstantiateInterceptor initiateInterceptor = new InstantiateInterceptor( injectedString );
		Session s = openSession( initiateInterceptor );

		Transaction t = s.beginTransaction();
		User u = new User( "Gavin", "nivag" );
		s.persist( u );
		t.commit();
		s.close();

		assertNull( u.getInjectedString() );
		u.setPassword( "blah" );

		s = openSession( initiateInterceptor );
		t = s.beginTransaction();

		User merged = ( User ) s.merge( u );
		assertEquals( injectedString, merged.getInjectedString() );
		assertEquals( u.getName(), merged.getName() );
		assertEquals( u.getPassword(), merged.getPassword() );

		merged.setInjectedString( null );

		User loaded = ( User ) s.load(User.class, merged.getName());
		// the session-bound instance was not instantiated by the interceptor, load simply returns it
		assertSame( merged, loaded );
		assertNull( merged.getInjectedString() );

		// flush the session and evict the merged instance from session to force an actual load
		s.flush();
		s.evict( merged );

		User reloaded = ( User ) s.load( User.class, merged.getName() );
		// Interceptor IS called for instantiating the persistent instance associated to the session when using load
		assertEquals( injectedString, reloaded.getInjectedString() );
		assertEquals( u.getName(), reloaded.getName() );
		assertEquals( u.getPassword(), reloaded.getPassword() );

		s.delete( reloaded );
		t.commit();
		s.close();
	}
