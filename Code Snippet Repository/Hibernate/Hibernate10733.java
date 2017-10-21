	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1, 2, 4 ).equals( getAuditReader().getRevisions( TernaryMapEntity.class, map1_id ) );
		assert Arrays.asList( 1, 2, 3, 4 ).equals( getAuditReader().getRevisions( TernaryMapEntity.class, map2_id ) );

		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( StrTestPrivSeqEntity.class, str1_id ) );
		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( StrTestPrivSeqEntity.class, str2_id ) );

		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( IntTestPrivSeqEntity.class, int1_id ) );
		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( IntTestPrivSeqEntity.class, int2_id ) );
	}
