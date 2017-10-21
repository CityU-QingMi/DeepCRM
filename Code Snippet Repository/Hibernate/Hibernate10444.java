	@Test
	public void testHistoryOfEle1() {
		EmbeddableListEntity1 rev1 = getAuditReader().find( EmbeddableListEntity1.class, ele1_id, 1 );
		EmbeddableListEntity1 rev2 = getAuditReader().find( EmbeddableListEntity1.class, ele1_id, 2 );
		EmbeddableListEntity1 rev3 = getAuditReader().find( EmbeddableListEntity1.class, ele1_id, 3 );
		EmbeddableListEntity1 rev4 = getAuditReader().find( EmbeddableListEntity1.class, ele1_id, 4 );

		assertEquals( Collections.singletonList( c3_1 ), rev1.getComponentList() );
		assertEquals( Arrays.asList( c3_1, c3_2 ), rev2.getComponentList() );
		assertEquals( Arrays.asList( c3_1, c3_2, c3_1 ), rev3.getComponentList() );
		assertEquals( Arrays.asList( c3_1, c3_1 ), rev4.getComponentList() );
	}
