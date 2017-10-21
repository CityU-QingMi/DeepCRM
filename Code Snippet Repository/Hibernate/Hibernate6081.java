	@Test
	public void testNativeNamedQueriesOrdinalParametersConflict2() {
		doInJPA( this::entityManagerFactory, entityManager -> {
					 Query query = entityManager.createNamedQuery( "NamedNativeQuery" );
					 query.setParameter( 1, GAME_TITLES[0] );
					 List list = query.getResultList();
					 assertEquals( 1, list.size() );

					 final Session session = entityManager.unwrap( Session.class );
					 final org.hibernate.query.Query sessionQuery = session.getNamedNativeQuery(
							 "NamedNativeQuery" );
					 sessionQuery.setParameter( 0, GAME_TITLES[0] );
					 list = sessionQuery.getResultList();

					 query.setParameter( 1, GAME_TITLES[0] );
					 assertEquals( 1, list.size() );
				 }
		);
	}
