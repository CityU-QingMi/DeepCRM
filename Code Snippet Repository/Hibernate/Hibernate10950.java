	@Test
	public void testHistoryOfEd1_1() {
		DoubleListJoinColumnBidirectionalRefIngEntity ing1 = getEntityManager().find(
				DoubleListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id
		);
		DoubleListJoinColumnBidirectionalRefIngEntity ing2 = getEntityManager().find(
				DoubleListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id
		);

		DoubleListJoinColumnBidirectionalRefEdEntity1 rev1 = getAuditReader().find(
				DoubleListJoinColumnBidirectionalRefEdEntity1.class,
				ed1_1_id,
				1
		);
		DoubleListJoinColumnBidirectionalRefEdEntity1 rev2 = getAuditReader().find(
				DoubleListJoinColumnBidirectionalRefEdEntity1.class,
				ed1_1_id,
				2
		);
		DoubleListJoinColumnBidirectionalRefEdEntity1 rev3 = getAuditReader().find(
				DoubleListJoinColumnBidirectionalRefEdEntity1.class,
				ed1_1_id,
				3
		);
		DoubleListJoinColumnBidirectionalRefEdEntity1 rev4 = getAuditReader().find(
				DoubleListJoinColumnBidirectionalRefEdEntity1.class,
				ed1_1_id,
				4
		);

		assertTrue( rev1.getOwner().equals( ing1 ) );
		assertTrue( rev2.getOwner().equals( ing1 ) );
		assertTrue( rev3.getOwner().equals( ing1 ) );
		assertTrue( rev4.getOwner().equals( ing2 ) );

		assertEquals( rev1.getData(), "ed1_1" );
		assertEquals( rev2.getData(), "ed1_1" );
		assertEquals( rev3.getData(), "ed1_1 bis" );
		assertEquals( rev4.getData(), "ed1_1 bis" );
	}
