	@Test
	public void testComponents() throws Exception {
		Session s = openSession();
		Transaction txn = s.beginTransaction();
		Foo foo = new Foo();
//		foo.setComponent( new FooComponent("foo", 69, null, new FooComponent("bar", 96, null, null) ) );
		s.save(foo);
		foo.getComponent().setName( "IFA" );
		txn.commit();
		s.close();

		foo.setComponent( null );

		s = openSession();
		txn = s.beginTransaction();
		s.load( foo, foo.getKey() );
		assertTrue(
			"save components",
			foo.getComponent().getName().equals("IFA") &&
			foo.getComponent().getSubcomponent().getName().equals("bar")
		);
		assertTrue( "cascade save via component", foo.getComponent().getGlarch() != null );
		foo.getComponent().getSubcomponent().setName("baz");
		txn.commit();
		s.close();

		foo.setComponent(null);

		s = openSession();
		txn = s.beginTransaction();
		s.load( foo, foo.getKey() );
		assertTrue(
			"update components",
			foo.getComponent().getName().equals("IFA") &&
			foo.getComponent().getSubcomponent().getName().equals("baz")
		);
		s.delete(foo);
		txn.commit();
		s.close();

		s = openSession();
		txn = s.beginTransaction();
		foo = new Foo();
		s.save( foo );
		foo.setCustom( new String[] { "one", "two" } );
		assertTrue( s.createQuery( "from Foo foo where foo.custom.s1 = 'one'" ).list().get(0)==foo );
		s.delete( foo );
		txn.commit();
		s.close();
	}
