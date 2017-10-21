	@Test
	public void testHistoryOfIng2() {
		ListJoinColumnBidirectionalRefEdEntity ed1 = getEntityManager().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed1_id
		);
		ListJoinColumnBidirectionalRefEdEntity ed2 = getEntityManager().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed2_id
		);

		ListJoinColumnBidirectionalRefIngEntity rev1 = getAuditReader().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				1
		);
		ListJoinColumnBidirectionalRefIngEntity rev2 = getAuditReader().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				2
		);
		ListJoinColumnBidirectionalRefIngEntity rev3 = getAuditReader().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				3
		);
		ListJoinColumnBidirectionalRefIngEntity rev4 = getAuditReader().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id,
				4
		);

		assertTrue( checkCollection( rev1.getReferences(), ed2 ) );
		assertTrue( checkCollection( rev2.getReferences() ) );
		assertTrue( checkCollection( rev3.getReferences() ) );
		assertTrue( checkCollection( rev4.getReferences(), ed1, ed2 ) );
	}
