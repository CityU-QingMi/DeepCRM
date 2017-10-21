	@Test
	public void testDereferenceLazyCollection() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		baz.setFooSet( new HashSet() );
		Foo foo = new Foo();
		baz.getFooSet().add(foo);
		s.save(foo);
		s.save(baz);
		foo.setBytes( "foobar".getBytes() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		foo = (Foo) s.get( Foo.class, foo.getKey() );
		assertTrue( Hibernate.isInitialized( foo.getBytes() ) );
		assertTrue( foo.getBytes().length==6 );
		baz = (Baz) s.get( Baz.class, baz.getCode() );
		assertTrue( baz.getFooSet().size()==1 );
		s.getTransaction().commit();
		s.close();

		sessionFactory().getCache().evictCollectionRegion("org.hibernate.test.legacy.Baz.fooSet");

		s = openSession();
		s.beginTransaction();
		baz = (Baz) s.get( Baz.class, baz.getCode() );
		assertFalse( Hibernate.isInitialized( baz.getFooSet() ) );
		baz.setFooSet(null);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		foo = (Foo) s.get( Foo.class, foo.getKey() );
		assertTrue( foo.getBytes().length==6 );
		baz = (Baz) s.get( Baz.class, baz.getCode() );
		assertFalse( Hibernate.isInitialized( baz.getFooSet() ) );
		assertTrue( baz.getFooSet().size()==0 );
		s.delete(baz);
		s.delete(foo);
		s.getTransaction().commit();
		s.close();
	}
