	@Test
	public void testPaginationWithHQLProjection() {
		doInHibernate( this::sessionFactory, session -> {
			for ( int i = 10; i < 20; i++ ) {
				session.persist( new Product2( i, "Kit" + i ) );
			}
			session.flush();
			session.clear();

			List list = session.createQuery(
					"select id, description as descr, (select max(id) from Product2) as maximum from Product2"
			).setFirstResult( 2 ).setMaxResults( 2 ).list();
			assertEquals( 19, ( (Object[]) list.get( 1 ) )[2] );

			list = session.createQuery( "select id, description, (select max(id) from Product2) from Product2 order by id" )
					.setFirstResult( 2 ).setMaxResults( 2 ).list();
			assertEquals( 2, list.size() );
			assertArrayEquals( new Object[] {12, "Kit12", 19}, (Object[]) list.get( 0 ));
			assertArrayEquals( new Object[] {13, "Kit13", 19}, (Object[]) list.get( 1 ));
		} );
	}
