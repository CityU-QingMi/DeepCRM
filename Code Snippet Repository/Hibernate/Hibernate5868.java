	@Test
	@TestForIssue( jiraKey = "" )
	public void testSubgraphsAreLoadededFromOrmXml() throws Exception {
		EntityManager entityManager = getOrCreateEntityManager();
		List<EntityGraph<? super Book>> lneg = entityManager.getEntityGraphs( Book.class );

		assertNotNull( lneg );
		Assert.assertEquals(2, lneg.size());
		for (EntityGraph<? super Book> neg : lneg){
			if (neg.getName().equalsIgnoreCase( "full" )){
				assertNotNull( neg.getAttributeNodes() );
				for (AttributeNode<?> n : neg.getAttributeNodes()){
					if (n.getAttributeName().equalsIgnoreCase( "authors" )) {
						Assert.assertEquals(1, n.getSubgraphs().size());
						java.util.List<javax.persistence.AttributeNode<?>> attributeNodes = n.getSubgraphs().get(Author.class).getAttributeNodes();
						assertNotNull("Subgraph attributes missing", attributeNodes);
						Assert.assertEquals("Subgraph wrong number of attributes ", 3, attributeNodes.size());
					}
				}
			}
		}
		entityManager.close();
	}
