	@Test
	public void testForceOuterJoin() throws Exception {
		if ( isOuterJoinFetchingDisabled() ) {
			return;
		}

		Session s = openSession();
		s.beginTransaction();
		Glarch g = new Glarch();
		FooComponent fc = new FooComponent();
		fc.setGlarch(g);
		FooProxy f = new Foo();
		FooProxy f2 = new Foo();
		f.setComponent(fc);
		f.setFoo(f2);
		s.save(f2);
		Serializable id = s.save(f);
		Serializable gid = s.getIdentifier( f.getComponent().getGlarch() );
		s.getTransaction().commit();
		s.close();

		sessionFactory().getCache().evictEntityRegion(Foo.class);

		s = openSession();
		s.beginTransaction();
		f = (FooProxy) s.load(Foo.class, id);
		assertFalse( Hibernate.isInitialized(f) );
		assertTrue( Hibernate.isInitialized( f.getComponent().getGlarch() ) ); //outer-join="true"
		assertFalse( Hibernate.isInitialized( f.getFoo() ) ); //outer-join="auto"
		assertEquals( s.getIdentifier( f.getComponent().getGlarch() ), gid );
		s.delete(f);
		s.delete( f.getFoo() );
		s.getTransaction().commit();
		s.close();
	}
