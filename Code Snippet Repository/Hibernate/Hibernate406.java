	@Test
	public void testEntityTuplizer() throws Exception {
		//tag::entity-tuplizer-dynamic-proxy-example[]
		Cuisine _cuisine = doInHibernateSessionBuilder(
				() -> sessionFactory()
						.withOptions()
						.interceptor( new EntityNameInterceptor() ),
				session -> {
			Cuisine cuisine = ProxyHelper.newProxy( Cuisine.class, null );
			cuisine.setName( "Française" );

			Country country = ProxyHelper.newProxy( Country.class, null );
			country.setName( "France" );

			cuisine.setCountry( country );
			session.persist( cuisine );

			return cuisine;
		} );

		doInHibernateSessionBuilder(
				() -> sessionFactory()
						.withOptions()
						.interceptor( new EntityNameInterceptor() ),
				session -> {
			Cuisine cuisine = session.get( Cuisine.class, _cuisine.getId() );

			assertEquals( "Française", cuisine.getName() );
			assertEquals( "France", cuisine.getCountry().getName() );
		} );
		//end::entity-tuplizer-dynamic-proxy-example[]
	}
