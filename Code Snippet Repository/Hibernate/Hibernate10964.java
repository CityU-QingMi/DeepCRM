	@Test
	public void testRevisionsCounts() {
		assertEquals(
				Arrays.asList( 1, 2, 4 ),
				getAuditReader().getRevisions( ListJoinColumnBidirectionalRefIngEntity.class, ing1_id )
		);
		assertEquals(
				Arrays.asList( 1, 2, 4 ),
				getAuditReader().getRevisions( ListJoinColumnBidirectionalRefIngEntity.class, ing2_id )
		);

		assertEquals(
				Arrays.asList( 1, 3, 4 ),
				getAuditReader().getRevisions( ListJoinColumnBidirectionalRefEdEntity.class, ed1_id )
		);
		assertEquals(
				Arrays.asList( 1, 2, 4 ),
				getAuditReader().getRevisions( ListJoinColumnBidirectionalRefEdEntity.class, ed2_id )
		);
	}
