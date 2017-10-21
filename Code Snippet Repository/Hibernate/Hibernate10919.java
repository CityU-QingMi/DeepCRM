	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1, 2, 3, 4, 5 ).equals( getAuditReader().getRevisions( SetRefEdEntity.class, ed1_id ) );
		assert Arrays.asList( 1, 4 ).equals( getAuditReader().getRevisions( SetRefEdEntity.class, ed2_id ) );

		assert Arrays.asList( 1, 2 ).equals( getAuditReader().getRevisions( SetRefIngEntity.class, ing1_id ) );
		assert Arrays.asList( 1, 3 ).equals( getAuditReader().getRevisions( SetRefIngEntity.class, ing2_id ) );
		assert Arrays.asList( 1, 4 ).equals( getAuditReader().getRevisions( SetRefIngEntity.class, ing3_id ) );
		assert Arrays.asList( 1, 5 ).equals( getAuditReader().getRevisions( SetRefIngEntity.class, ing4_id ) );
	}
