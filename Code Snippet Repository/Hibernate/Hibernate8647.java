	@Test
	public void testArrayCriteria() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Baz baz = new Baz();
		s.save(baz);
		Foo foo1 = new Foo();
		s.save(foo1);
		baz.setFooArray( new FooProxy[] { foo1 } );

		s.flush();
		s.clear();

		baz = ( Baz ) s.createCriteria(Baz.class).createCriteria( "fooArray" ).uniqueResult();
		assertEquals( 1, baz.getFooArray().length );

		t.rollback();
		s.close();
	}
