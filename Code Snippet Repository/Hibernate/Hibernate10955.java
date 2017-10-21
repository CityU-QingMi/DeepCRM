	@Test
	public void testHistoryOfIng2() {
		IndexedListJoinColumnBidirectionalRefEdEntity ed2 = getEntityManager().find(
				IndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed2_id
		);

		IndexedListJoinColumnBidirectionalRefIngEntity rev1 = getAuditReader().find(
				IndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				1
		);
		IndexedListJoinColumnBidirectionalRefIngEntity rev2 = getAuditReader().find(
				IndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				2
		);
		IndexedListJoinColumnBidirectionalRefIngEntity rev3 = getAuditReader().find(
				IndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				3
		);
		IndexedListJoinColumnBidirectionalRefIngEntity rev4 = getAuditReader().find(
				IndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				4
		);

		assertEquals( rev1.getReferences().size(), 0 );

		assertEquals( rev2.getReferences().size(), 1 );
		assertEquals( rev2.getReferences().get( 0 ), ed2 );

		assertEquals( rev3.getReferences().size(), 1 );
		assertEquals( rev3.getReferences().get( 0 ), ed2 );

		assertEquals( rev4.getReferences().size(), 0 );
	}
