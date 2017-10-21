	@Test
	public void testBasicSubgraphBuilding() {
		EntityManager em = getOrCreateEntityManager();
		EntityGraph<Entity1> graphRoot = em.createEntityGraph( Entity1.class );
		Subgraph<Entity1> parentGraph = graphRoot.addSubgraph( "parent" );
		Subgraph<Entity1> childGraph = graphRoot.addSubgraph( "children" );

		assertNull( graphRoot.getName() );
		assertEquals( 2, graphRoot.getAttributeNodes().size() );
		assertTrue(
				graphRoot.getAttributeNodes().get( 0 ).getSubgraphs().containsValue( parentGraph )
						|| graphRoot.getAttributeNodes().get( 0 ).getSubgraphs().containsValue( childGraph )
		);
		assertTrue(
				graphRoot.getAttributeNodes().get( 1 ).getSubgraphs().containsValue( parentGraph )
						|| graphRoot.getAttributeNodes().get( 1 ).getSubgraphs().containsValue( childGraph )
		);
	}
