	@Test
	public void testHistoryOfEd3() {
		ChildIndexedListJoinColumnBidirectionalRefIngEntity ing1 = getEntityManager().find(
				ChildIndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id
		);

		ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity rev1 = getAuditReader().find(
				ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed3_id,
				1
		);
		ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity rev2 = getAuditReader().find(
				ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed3_id,
				2
		);
		ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity rev3 = getAuditReader().find(
				ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed3_id,
				3
		);
		ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity rev4 = getAuditReader().find(
				ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed3_id,
				4
		);

		assertTrue( rev1.getOwner().equals( ing1 ) );
		assertTrue( rev2.getOwner().equals( ing1 ) );
		assertTrue( rev3.getOwner().equals( ing1 ) );
		assertTrue( rev4.getOwner().equals( ing1 ) );

		assertEquals( rev1.getPosition(), Integer.valueOf( 2 ) );
		assertEquals( rev2.getPosition(), Integer.valueOf( 1 ) );
		assertEquals( rev3.getPosition(), Integer.valueOf( 0 ) );
		assertEquals( rev4.getPosition(), Integer.valueOf( 1 ) );
	}
