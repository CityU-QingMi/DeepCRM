	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1, 2 ).equals(
				getAuditReader().getRevisions(
						VersionsJoinTableRangeComponentTestEntity.class,
						vjrcte_id
				)
		);
		assert Arrays.asList( 2 ).equals(
				getAuditReader().getRevisions(
						VersionsJoinTableRangeTestEntity.class, vjtrte_id
				)
		);
		assert Arrays.asList( 2 ).equals(
				getAuditReader().getRevisions(
						VersionsJoinTableRangeTestAlternateEntity.class,
						vjtrtae_id1
				)
		);
	}
