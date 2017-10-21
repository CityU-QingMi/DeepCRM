	@Test
	@TestForIssue(jiraKey = "")
	public void testPaginationWithMaxOnly() {
		doInHibernate( this::sessionFactory, session -> {
			for ( int i = 30; i < 40; i++ ) {
				session.persist( new Product2( i, "Kit" + i ) );
			}
			session.flush();
			session.clear();

			List list = session.createQuery( "from Product2 order by id" ).setFirstResult( 0 ).setMaxResults( 2 ).list();
			assertEquals( Arrays.asList( new Product2( 30, "Kit30" ), new Product2( 31, "Kit31" ) ), list );

			list = session.createQuery( "select distinct p from Product2 p order by p.id" ).setMaxResults( 1 ).list();
			assertEquals( Collections.singletonList( new Product2( 30, "Kit30" ) ), list );
		} );
	}
