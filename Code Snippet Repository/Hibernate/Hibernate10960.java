	@Test
	public void testHistoryOfIng2() {
		ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity ed2 = getEntityManager().find(
				ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed2_id
		);

		ChildIndexedListJoinColumnBidirectionalRefIngEntity rev1 = getAuditReader().find(
				ChildIndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				1
		);
		ChildIndexedListJoinColumnBidirectionalRefIngEntity rev2 = getAuditReader().find(
				ChildIndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				2
		);
		ChildIndexedListJoinColumnBidirectionalRefIngEntity rev3 = getAuditReader().find(
				ChildIndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				3
		);
		ChildIndexedListJoinColumnBidirectionalRefIngEntity rev4 = getAuditReader().find(
				ChildIndexedListJoinColumnBidirectionalRefIngEntity.class,
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
