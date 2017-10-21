	@Test
	@TestForIssue( jiraKey = "" )
	public void testSelectingKeyOfMapJoin() {
		doInJPA( this::entityManagerFactory, entityManager -> {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<Node> query = cb.createQuery( Node.class );
			Root<Batch> root = query.from( Batch.class );

			MapJoin nodes = (MapJoin) root.join( "batchNodeMetadata" );

			query.select( nodes.key() );
			query.where( cb.equal( root.get( "id" ), 1 ) );

			entityManager.createQuery( query ).getResultList();
		} );
	}
