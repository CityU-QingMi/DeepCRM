	@Test
	public void testRevisionsCounts() {
		assertEquals(
				Arrays.asList( 1, 2, 4 ), getAuditReader().getRevisions(
				DoubleListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id
		)
		);
		assertEquals(
				Arrays.asList( 1, 2, 4 ), getAuditReader().getRevisions(
				DoubleListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id
		)
		);

		assertEquals(
				Arrays.asList( 1, 3, 4 ), getAuditReader().getRevisions(
				DoubleListJoinColumnBidirectionalRefEdEntity1.class,
				ed1_1_id
		)
		);
		assertEquals(
				Arrays.asList( 1, 2, 4 ), getAuditReader().getRevisions(
				DoubleListJoinColumnBidirectionalRefEdEntity1.class,
				ed1_2_id
		)
		);

		assertEquals(
				Arrays.asList( 1, 4 ), getAuditReader().getRevisions(
				DoubleListJoinColumnBidirectionalRefEdEntity2.class,
				ed2_1_id
		)
		);
		assertEquals(
				Arrays.asList( 1, 2, 3 ), getAuditReader().getRevisions(
				DoubleListJoinColumnBidirectionalRefEdEntity2.class,
				ed2_2_id
		)
		);
	}
