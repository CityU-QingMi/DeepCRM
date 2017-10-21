	@Test
	public void testProxyFromQuery() {
		doInHibernate( this::sessionFactory, s -> {
			A a = new A();
			a.id = 1L;
			a.b = new B();
			a.b.id = 1L;
			s.persist( a );
		} );

		doInHibernate( this::sessionFactory, s -> {
			A a = s.find( A.class, 1L );
			List<B> result = s.createQuery( "FROM " + B.class.getName() + " b", B.class ).getResultList();
			assertEquals( 1, result.size() );
			assertTrue( a.b == result.get( 0 ) );
		} );
	}
