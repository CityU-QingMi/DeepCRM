	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( UniRefEdEntity.class, ed1_id ) );
		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( UniRefEdEntity.class, ed2_id ) );
		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( UniRefEdEntity.class, ed3_id ) );
		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( UniRefEdEntity.class, ed4_id ) );

		assert Arrays.asList( 1, 2 ).equals( getAuditReader().getRevisions( UniRefIngEntity.class, ing1_id ) );
		assert Arrays.asList( 1, 3 ).equals( getAuditReader().getRevisions( UniRefIngEntity.class, ing2_id ) );
	}
