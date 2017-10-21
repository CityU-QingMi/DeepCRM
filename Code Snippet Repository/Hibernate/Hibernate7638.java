	@Test
	@TestForIssue(jiraKey = "")
	public void testPaginationWithCastOperator() {
		doInHibernate( this::sessionFactory, session -> {
			for ( int i = 40; i < 50; i++ ) {
				session.persist( new Product2( i, "Kit" + i ) );
			}
			session.flush();
			session.clear();

			List<Object[]> list = session.createQuery( "select p.id, cast(p.id as string) as string_id from Product2 p order by p.id" )
					.setFirstResult( 1 ).setMaxResults( 2 ).list();
			assertEquals( 2, list.size() );
			assertArrayEquals( new Object[] { 41, "41" }, list.get( 0 ) );
			assertArrayEquals( new Object[] { 42, "42" }, list.get( 1 ) );
		} );
	}
