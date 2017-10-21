	@Test
	public void testHistoryOfIng1() {
		ListJoinColumnBidirectionalRefEdEntity ed1_fromRev1 = new ListJoinColumnBidirectionalRefEdEntity(
				ed1_id,
				"ed1",
				null
		);
		ListJoinColumnBidirectionalRefEdEntity ed1_fromRev3 = new ListJoinColumnBidirectionalRefEdEntity(
				ed1_id,
				"ed1 bis",
				null
		);
		ListJoinColumnBidirectionalRefEdEntity ed2 = getEntityManager().find(
				ListJoinColumnBidirectionalRefEdEntity.class,
				ed2_id
		);

		ListJoinColumnBidirectionalRefIngEntity rev1 = getAuditReader().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id,
				1
		);
		ListJoinColumnBidirectionalRefIngEntity rev2 = getAuditReader().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id,
				2
		);
		ListJoinColumnBidirectionalRefIngEntity rev3 = getAuditReader().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id,
				3
		);
		ListJoinColumnBidirectionalRefIngEntity rev4 = getAuditReader().find(
				ListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id,
				4
		);

		assertTrue( checkCollection( rev1.getReferences(), ed1_fromRev1 ) );
		assertTrue( checkCollection( rev2.getReferences(), ed1_fromRev1, ed2 ) );
		assertTrue( checkCollection( rev3.getReferences(), ed1_fromRev3, ed2 ) );
		assertTrue( checkCollection( rev4.getReferences() ) );
	}
