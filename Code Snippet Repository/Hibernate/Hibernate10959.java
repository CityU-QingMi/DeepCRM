	@Test
	public void testRevisionsCounts() {
		assertEquals(
				Arrays.asList( 1, 2, 3, 4 ), getAuditReader().getRevisions(
				ChildIndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing1_id
		)
		);
		assertEquals(
				Arrays.asList( 1, 2, 4 ), getAuditReader().getRevisions(
				ChildIndexedListJoinColumnBidirectionalRefIngEntity.class,
				ing2_id
		)
		);

		assertEquals(
				Arrays.asList( 1, 3, 4 ), getAuditReader().getRevisions(
				ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed1_id
		)
		);
		assertEquals(
				Arrays.asList( 1, 2, 4 ), getAuditReader().getRevisions(
				ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed2_id
		)
		);
		assertEquals(
				Arrays.asList( 1, 2, 3, 4 ), getAuditReader().getRevisions(
				ParentOwnedIndexedListJoinColumnBidirectionalRefEdEntity.class,
				ed3_id
		)
		);
	}
