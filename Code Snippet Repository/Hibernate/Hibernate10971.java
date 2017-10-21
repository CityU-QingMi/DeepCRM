	@Test
	public void testHistoryOfIng2() {
		ListJoinColumnBidirectionalInheritanceRefEdParentEntity ed2 = getEntityManager().find(
				ListJoinColumnBidirectionalInheritanceRefEdParentEntity.class,
				ed2_id
		);

		ListJoinColumnBidirectionalInheritanceRefIngEntity rev1 = getAuditReader().find(
				ListJoinColumnBidirectionalInheritanceRefIngEntity.class,
				ing2_id,
				1
		);
		ListJoinColumnBidirectionalInheritanceRefIngEntity rev2 = getAuditReader().find(
				ListJoinColumnBidirectionalInheritanceRefIngEntity.class,
				ing2_id,
				2
		);

		assertTrue( checkCollection( rev1.getReferences(), ed2 ) );
		assertTrue( checkCollection( rev2.getReferences() ) );
	}
