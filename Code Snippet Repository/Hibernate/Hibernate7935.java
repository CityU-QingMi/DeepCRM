	@Test
	public void testJpaTypeOperator() {
		// just checking syntax here...
		Session s = openSession();
		s.beginTransaction();

		///////////////////////////////////////////////////////////////
		// where clause
		// control
		s.createQuery( "from Animal a where a.class = Dog" ).list();
        // test
		s.createQuery( "from Animal a where type(a) = Dog" ).list();

		///////////////////////////////////////////////////////////////
		// select clause (at some point we should unify these)
		// control
		Query query = s.createQuery( "select a.class from Animal a where a.class = Dog" );
		query.list(); // checks syntax
		assertEquals( 1, query.getReturnTypes().length );
		assertEquals( Integer.class, query.getReturnTypes()[0].getReturnedClass() ); // always integer for joined
        // test
		query = s.createQuery( "select type(a) from Animal a where type(a) = Dog" );
		query.list(); // checks syntax
		assertEquals( 1, query.getReturnTypes().length );
		assertEquals( DiscriminatorType.class, query.getReturnTypes()[0].getClass() );
		assertEquals( Class.class, query.getReturnTypes()[0].getReturnedClass() );

		s.getTransaction().commit();
		s.close();
	}
