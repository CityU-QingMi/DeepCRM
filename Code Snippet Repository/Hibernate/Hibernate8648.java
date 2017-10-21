	@Test
	public void testLazyManyToOneHQL() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		s.save(baz);
		Foo foo1 = new Foo();
		s.save(foo1);
		baz.setFoo( foo1 );

		s.flush();
		s.clear();

		baz = ( Baz ) s.createQuery("from Baz b").uniqueResult();
		assertFalse( Hibernate.isInitialized( baz.getFoo() ) );
		assertTrue( baz.getFoo() instanceof HibernateProxy );

		t.rollback();
		s.close();
	}
