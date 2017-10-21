	@Test
	public void testNativeNamedQueriesOrdinalParametersAreZeroBased() {
		doInHibernate( this::sessionFactory, session -> {
					 Query query = session.getNamedNativeQuery( "NamedNativeQuery" );
					 query.setParameter( 0, GAME_TITLES[0] );
					 List list = query.getResultList();
					 assertEquals( 1, list.size() );
				 }
		);
	}
