	@Test
	public void testLazyManyToOneGet() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		s.save(baz);
		Foo foo1 = new Foo();
		s.save(foo1);
		baz.setFoo( foo1 );

		s.flush();
		s.clear();

		baz = ( Baz ) s.get( Baz.class, baz.getCode() );
		assertTrue( Hibernate.isInitialized( baz.getFoo() ) );
		assertFalse( baz.getFoo() instanceof HibernateProxy );

		t.rollback();
		s.close();
	}
