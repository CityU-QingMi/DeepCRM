	@Test
	public void testHistoryOfUniId1() {
		VersionsJoinTableRangeTestEntity vjtrte = getEntityManager().find(
				VersionsJoinTableRangeTestEntity.class, vjtrte_id
		);
		VersionsJoinTableRangeTestAlternateEntity vjtrtae = getEntityManager()
				.find(
						VersionsJoinTableRangeTestAlternateEntity.class,
						vjtrtae_id1
				);

		VersionsJoinTableRangeComponentTestEntity rev1 = getAuditReader().find(
				VersionsJoinTableRangeComponentTestEntity.class, vjrcte_id, 1
		);
		VersionsJoinTableRangeComponentTestEntity rev2 = getAuditReader().find(
				VersionsJoinTableRangeComponentTestEntity.class, vjrcte_id, 2
		);

		assert rev1.getComponent1().getRange().size() == 0;
		assert rev1.getComponent2().getRange().size() == 0;

		assert rev2.getComponent1().getRange().size() == 1;
		assert rev2.getComponent1().getRange().get( 0 ).equals( vjtrte );
		assert rev2.getComponent2().getRange().size() == 1;
		assert rev2.getComponent2().getRange().get( 0 ).equals( vjtrtae );
	}
