	@Test
	public void testNamedQueriesOrdinalParametersAreZeroBased() {
		doInHibernate( this::sessionFactory, session -> {
					 Query query = session.getNamedQuery( "NamedQuery" );
					 query.setParameter( 0, GAME_TITLES[0] );
					 List list = query.getResultList();
					 assertEquals( 1, list.size() );
				 }
		);
	}
