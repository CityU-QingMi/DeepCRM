	@Test
	public void testHistoryOfEd1() {
		ListJoinColumnBidirectionalInheritanceRefIngEntity ing1 = getEntityManager().find(
				ListJoinColumnBidirectionalInheritanceRefIngEntity.class,
				ing1_id
		);

		ListJoinColumnBidirectionalInheritanceRefEdParentEntity rev1 = getAuditReader().find(
				ListJoinColumnBidirectionalInheritanceRefEdParentEntity.class,
				ed1_id,
				1
		);
		ListJoinColumnBidirectionalInheritanceRefEdParentEntity rev2 = getAuditReader().find(
				ListJoinColumnBidirectionalInheritanceRefEdParentEntity.class,
				ed1_id,
				2
		);

		assertTrue( rev1.getOwner().equals( ing1 ) );
		assertTrue( rev2.getOwner().equals( ing1 ) );
	}
