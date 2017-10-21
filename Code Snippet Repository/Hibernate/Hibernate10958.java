	@Test
	public void testHistoryOfEd3() {
		IndexedListJoinColumnBidirectionalRefIngEntity ing1 = getEntityManager().find(
				IndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id
		);

		IndexedListJoinColumnBidirectionalRefEdEntity rev1 = getAuditReader().find(
				IndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed3_id,
				1
		);
		IndexedListJoinColumnBidirectionalRefEdEntity rev2 = getAuditReader().find(
				IndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed3_id,
				2
		);
		IndexedListJoinColumnBidirectionalRefEdEntity rev3 = getAuditReader().find(
				IndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed3_id,
				3
		);
		IndexedListJoinColumnBidirectionalRefEdEntity rev4 = getAuditReader().find(
				IndexedListJoinColumnBidirectionalRefEdEntity.class,
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
