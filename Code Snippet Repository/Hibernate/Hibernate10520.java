	@Test
	public void testHistoryOfId1() {
		NotAuditedManyToOneComponentTestEntity ver1 = new NotAuditedManyToOneComponentTestEntity(
				mtocte_id1,
				new NotAuditedManyToOneComponent( null, "data1" )
		);
		NotAuditedManyToOneComponentTestEntity ver2 = new NotAuditedManyToOneComponentTestEntity(
				mtocte_id1,
				new NotAuditedManyToOneComponent( null, "data2" )
		);

		assert getAuditReader().find( NotAuditedManyToOneComponentTestEntity.class, mtocte_id1, 1 ).equals( ver1 );
		assert getAuditReader().find( NotAuditedManyToOneComponentTestEntity.class, mtocte_id1, 2 ).equals( ver2 );
	}
