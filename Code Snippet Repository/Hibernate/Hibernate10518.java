	@Test
	public void testHistoryOfId1() {
		StrTestEntity ste1 = getEntityManager().find( StrTestEntity.class, ste_id1 );
		StrTestEntity ste2 = getEntityManager().find( StrTestEntity.class, ste_id2 );

		ManyToOneComponentTestEntity ver2 = new ManyToOneComponentTestEntity(
				mtocte_id1, new ManyToOneComponent(
				ste1,
				"data1"
		)
		);
		ManyToOneComponentTestEntity ver3 = new ManyToOneComponentTestEntity(
				mtocte_id1, new ManyToOneComponent(
				ste2,
				"data1"
		)
		);

		assert getAuditReader().find( ManyToOneComponentTestEntity.class, mtocte_id1, 1 ) == null;
		assert getAuditReader().find( ManyToOneComponentTestEntity.class, mtocte_id1, 2 ).equals( ver2 );
		assert getAuditReader().find( ManyToOneComponentTestEntity.class, mtocte_id1, 3 ).equals( ver3 );
	}
