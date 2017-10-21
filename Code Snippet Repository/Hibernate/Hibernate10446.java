	@Test
	public void testManyToOneComponentList() {
		// Revision 1: many-to-one component1 in the list
		EmbeddableListEntity2 rev1 = getAuditReader().find( EmbeddableListEntity2.class, ele_id1, 1 );
		assertNotNull( "Revision not found", rev1 );
		assertTrue( "The component collection was not audited", rev1.getComponentList().size() > 0 );
		assertEquals(
				"The component primitive property was not audited",
				"dataComponent1", rev1.getComponentList().get( 0 ).getData()
		);
		assertEquals(
				"The component manyToOne reference was not audited",
				entity1, rev1.getComponentList().get( 0 ).getEntity()
		);
	}
