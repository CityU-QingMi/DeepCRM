	@Test
	public void testAttributeNodesAreAvailable() {
		EntityManager em = getOrCreateEntityManager();
			EntityGraph graph = em.getEntityGraph( "name_salary_graph" );
			assertNotNull( graph );

			List<AttributeNode<?>> list =  graph.getAttributeNodes();
			assertNotNull( list );
			assertTrue("expected list.size() is two but actual list size is " + list.size(), 2 == list.size() );

			AttributeNode attributeNode1 = list.get(0);
			AttributeNode attributeNode2 = list.get(1);
			assertNotNull( attributeNode1 );
			assertNotNull( attributeNode2 );

			assertTrue( "node1 attribute name is expected to be either 'name' or 'salary' but actually is "+attributeNode1.getAttributeName(),
					"name".equals(attributeNode1.getAttributeName()) || "salary".equals(attributeNode1.getAttributeName()));

			assertTrue( "node2 attribute name is expected to be either 'name' or 'salary' but actually is "+attributeNode2.getAttributeName(),
					"name".equals(attributeNode2.getAttributeName()) || "salary".equals(attributeNode2.getAttributeName()));
	}
