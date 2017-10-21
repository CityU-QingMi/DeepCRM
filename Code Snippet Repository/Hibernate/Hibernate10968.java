	@Test
	public void testHistoryOfEd2() {
		ListJoinColumnBidirectionalRefIngEntity ing1 = getEntityManager().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id
		);
		ListJoinColumnBidirectionalRefIngEntity ing2 = getEntityManager().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id
		);

		ListJoinColumnBidirectionalRefEdEntity rev1 = getAuditReader().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed2_id,
				1
		);
		ListJoinColumnBidirectionalRefEdEntity rev2 = getAuditReader().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed2_id,
				2
		);
		ListJoinColumnBidirectionalRefEdEntity rev3 = getAuditReader().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed2_id,
				3
		);
		ListJoinColumnBidirectionalRefEdEntity rev4 = getAuditReader().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed2_id,
				4
		);

		assertTrue( rev1.getOwner().equals( ing2 ) );
		assertTrue( rev2.getOwner().equals( ing1 ) );
		assertTrue( rev3.getOwner().equals( ing1 ) );
		assertTrue( rev4.getOwner().equals( ing2 ) );

		assertEquals( rev1.getData(), "ed2" );
		assertEquals( rev2.getData(), "ed2" );
		assertEquals( rev3.getData(), "ed2" );
		assertEquals( rev4.getData(), "ed2" );
	}
