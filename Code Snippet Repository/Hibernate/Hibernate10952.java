	@Test
	public void testHistoryOfEd2_1() {
		DoubleListJoinColumnBidirectionalRefIngEntity ing1 = getEntityManager().find(
				DoubleListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id
		);
		DoubleListJoinColumnBidirectionalRefIngEntity ing2 = getEntityManager().find(
				DoubleListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id
		);

		DoubleListJoinColumnBidirectionalRefEdEntity2 rev1 = getAuditReader().find(
				DoubleListJoinColumnBidirectionalRefEdEntity2.class,
				ed2_1_id,
				1
		);
		DoubleListJoinColumnBidirectionalRefEdEntity2 rev2 = getAuditReader().find(
				DoubleListJoinColumnBidirectionalRefEdEntity2.class,
				ed2_1_id,
				2
		);
		DoubleListJoinColumnBidirectionalRefEdEntity2 rev3 = getAuditReader().find(
				DoubleListJoinColumnBidirectionalRefEdEntity2.class,
				ed2_1_id,
				3
		);
		DoubleListJoinColumnBidirectionalRefEdEntity2 rev4 = getAuditReader().find(
				DoubleListJoinColumnBidirectionalRefEdEntity2.class,
				ed2_1_id,
				4
		);

		assertTrue( rev1.getOwner().equals( ing1 ) );
		assertTrue( rev2.getOwner().equals( ing1 ) );
		assertTrue( rev3.getOwner().equals( ing1 ) );
		assertTrue( rev4.getOwner().equals( ing2 ) );

		assertEquals( rev1.getData(), "ed2_1" );
		assertEquals( rev2.getData(), "ed2_1" );
		assertEquals( rev3.getData(), "ed2_1" );
		assertEquals( rev4.getData(), "ed2_1" );
	}
