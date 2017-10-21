	@Test
	@SuppressWarnings("")
	public void testBasicGraphImmutability() {
		EntityManager em = getOrCreateEntityManager();
		EntityGraph<Entity1> graphRoot = em.createEntityGraph( Entity1.class );
		graphRoot.addSubgraph( "parent" );
		graphRoot.addSubgraph( "children" );

		em.getEntityManagerFactory().addNamedEntityGraph( "immutable", graphRoot );

		graphRoot = (EntityGraph<Entity1>) em.getEntityGraph( "immutable" );

		assertEquals( "immutable", graphRoot.getName() );
		assertEquals( 2, graphRoot.getAttributeNodes().size() );
		try {
			graphRoot.addAttributeNodes( "parent" );
			fail( "Should have failed" );
		}
		catch (IllegalStateException ignore) {
			// expected outcome
		}

		for ( AttributeNode attrNode : graphRoot.getAttributeNodes() ) {
			assertEquals( 1, attrNode.getSubgraphs().size() );
			Subgraph subgraph = (Subgraph) attrNode.getSubgraphs().values().iterator().next();
			try {
				graphRoot.addAttributeNodes( "parent" );
				fail( "Should have failed" );
			}
			catch (IllegalStateException ignore) {
				// expected outcome
			}
		}
	}
