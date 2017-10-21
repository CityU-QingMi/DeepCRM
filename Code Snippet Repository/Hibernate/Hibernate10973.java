	@Test
	public void testHistoryOfEd2() {
		ListJoinColumnBidirectionalInheritanceRefIngEntity ing1 = getEntityManager().find(
				ListJoinColumnBidirectionalInheritanceRefIngEntity.class,
				ing1_id
		);
		ListJoinColumnBidirectionalInheritanceRefIngEntity ing2 = getEntityManager().find(
				ListJoinColumnBidirectionalInheritanceRefIngEntity.class,
				ing2_id
		);

		ListJoinColumnBidirectionalInheritanceRefEdParentEntity rev1 = getAuditReader().find(
				ListJoinColumnBidirectionalInheritanceRefEdParentEntity.class,
				ed2_id,
				1
		);
		ListJoinColumnBidirectionalInheritanceRefEdParentEntity rev2 = getAuditReader().find(
				ListJoinColumnBidirectionalInheritanceRefEdParentEntity.class,
				ed2_id,
				2
		);

		assertTrue( rev1.getOwner().equals( ing2 ) );
		assertTrue( rev2.getOwner().equals( ing1 ) );
	}
