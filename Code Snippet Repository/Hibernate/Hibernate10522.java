	@Test
	public void testHistoryOfId1() {
		StrTestEntity ste1 = getEntityManager().find( StrTestEntity.class, ste_id1 );
		StrTestEntity ste2 = getEntityManager().find( StrTestEntity.class, ste_id2 );

		OneToManyComponentTestEntity ver2 = new OneToManyComponentTestEntity(
				otmcte_id1, new OneToManyComponent(
				"data1"
		)
		);
		ver2.getComp1().getEntities().add( ste1 );
		OneToManyComponentTestEntity ver3 = new OneToManyComponentTestEntity(
				otmcte_id1, new OneToManyComponent(
				"data1"
		)
		);
		ver3.getComp1().getEntities().add( ste1 );
		ver3.getComp1().getEntities().add( ste2 );

		assert getAuditReader().find( OneToManyComponentTestEntity.class, otmcte_id1, 1 ) == null;
		assert getAuditReader().find( OneToManyComponentTestEntity.class, otmcte_id1, 2 ).equals( ver2 );
		assert getAuditReader().find( OneToManyComponentTestEntity.class, otmcte_id1, 3 ).equals( ver3 );
	}
