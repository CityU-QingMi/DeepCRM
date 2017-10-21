	@Test
	public void testPaginationWithHQL() {
		doInHibernate( this::sessionFactory, session -> {
			for ( int i = 20; i < 30; i++ ) {
				session.persist( new Product2( i, "Kit" + i ) );
			}
			session.flush();
			session.clear();

			List list = session.createQuery( "from Product2 order by id" ).setFirstResult( 3 ).setMaxResults( 2 ).list();
			assertEquals( Arrays.asList( new Product2( 23, "Kit23" ), new Product2( 24, "Kit24" ) ), list );
		} );
	}
