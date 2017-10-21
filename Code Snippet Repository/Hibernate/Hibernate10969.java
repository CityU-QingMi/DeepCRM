	@Test
	public void testRevisionsCounts() {
		assertEquals(
				Arrays.asList( 1, 2 ), getAuditReader().getRevisions(
				ListJoinColumnBidirectionalInheritanceRefIngEntity.class,
				ing1_id
		)
		);
		assertEquals(
				Arrays.asList( 1, 2 ), getAuditReader().getRevisions(
				ListJoinColumnBidirectionalInheritanceRefIngEntity.class,
				ing2_id
		)
		);

		assertEquals(
				Arrays.asList( 1 ), getAuditReader().getRevisions(
				ListJoinColumnBidirectionalInheritanceRefEdParentEntity.class,
				ed1_id
		)
		);
		assertEquals(
				Arrays.asList( 1, 2 ), getAuditReader().getRevisions(
				ListJoinColumnBidirectionalInheritanceRefEdParentEntity.class,
				ed2_id
		)
		);
	}
