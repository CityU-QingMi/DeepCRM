	@Test
	@TestForIssue(jiraKey = "")
	public void testPaginationWithScalarQuery() throws Exception {
		doInHibernate( this::sessionFactory, session -> {
			for ( int i = 0; i < 10; i++ ) {
				session.persist( new Product2( i, "Kit" + i ) );
			}
			session.flush();
			session.clear();

			List list = session.createNativeQuery( "select id from Product2 where description like 'Kit%' order by id" ).list();
			assertEquals(Integer.class, list.get(0).getClass()); // scalar result is an Integer

			list = session.createNativeQuery( "select id from Product2 where description like 'Kit%' order by id" ).setFirstResult( 2 ).setMaxResults( 2 ).list();
			assertEquals(Integer.class, list.get(0).getClass()); // this fails without patch, as result suddenly has become an array

			// same once again with alias
			list = session.createNativeQuery( "select id as myint from Product2 where description like 'Kit%' order by id asc" ).setFirstResult( 2 ).setMaxResults( 2 ).list();
			assertEquals(Integer.class, list.get(0).getClass());
		} );
	}
