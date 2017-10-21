	@Test
	public void testHistoryOfIng1() {
		ListJoinColumnBidirectionalInheritanceRefEdParentEntity ed1 = getEntityManager().find(
				ListJoinColumnBidirectionalInheritanceRefEdParentEntity.class,
				ed1_id
		);
		ListJoinColumnBidirectionalInheritanceRefEdParentEntity ed2 = getEntityManager().find(
				ListJoinColumnBidirectionalInheritanceRefEdParentEntity.class,
				ed2_id
		);

		ListJoinColumnBidirectionalInheritanceRefIngEntity rev1 = getAuditReader().find(
				ListJoinColumnBidirectionalInheritanceRefIngEntity.class,
				ing1_id,
				1
		);
		ListJoinColumnBidirectionalInheritanceRefIngEntity rev2 = getAuditReader().find(
				ListJoinColumnBidirectionalInheritanceRefIngEntity.class,
				ing1_id,
				2
		);

		assertTrue( checkCollection( rev1.getReferences(), ed1 ) );
		assertTrue( checkCollection( rev2.getReferences(), ed1, ed2 ) );
	}
