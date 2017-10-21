	@Test
	public void testLazyManyToOneCriteria() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		s.save(baz);
		Foo foo1 = new Foo();
		s.save(foo1);
		baz.setFoo( foo1 );

		s.flush();
		s.clear();

		baz = ( Baz ) s.createCriteria( Baz.class ).uniqueResult();
		assertTrue( Hibernate.isInitialized( baz.getFoo() ) );
		assertFalse( baz.getFoo() instanceof HibernateProxy );

		t.rollback();
		s.close();
	}
