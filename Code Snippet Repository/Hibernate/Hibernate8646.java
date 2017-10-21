	@Test
	public void testArrayHQL() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		s.save(baz);
		Foo foo1 = new Foo();
		s.save(foo1);
		baz.setFooArray( new FooProxy[] { foo1 } );

		s.flush();
		s.clear();

		baz = ( Baz ) s.createQuery("from Baz b left join fetch b.fooArray").uniqueResult();
		assertEquals( 1, baz.getFooArray().length );

		t.rollback();
		s.close();
	}
