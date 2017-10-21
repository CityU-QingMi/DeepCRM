	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1, 2 ).equals(
				getAuditReader().getRevisions(
						JoinEmbIdNamingRefEdEntity.class,
						ed_id1
				)
		);
		assert Arrays.asList( 1, 2 ).equals(
				getAuditReader().getRevisions(
						JoinEmbIdNamingRefEdEntity.class,
						ed_id2
				)
		);
		assert Arrays.asList( 1, 2 ).equals(
				getAuditReader().getRevisions(
						JoinEmbIdNamingRefIngEntity.class,
						ing_id1
				)
		);
	}
