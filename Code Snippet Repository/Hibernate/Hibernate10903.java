	@Test
	public void testHistoryOfEdIng1() {
		ListRefEdEntity ed1 = getEntityManager().find( ListRefEdEntity.class, ed1_id );
		ListRefEdEntity ed2 = getEntityManager().find( ListRefEdEntity.class, ed2_id );

		ListRefIngEntity rev1 = getAuditReader().find( ListRefIngEntity.class, ing1_id, 1 );
		ListRefIngEntity rev2 = getAuditReader().find( ListRefIngEntity.class, ing1_id, 2 );
		ListRefIngEntity rev3 = getAuditReader().find( ListRefIngEntity.class, ing1_id, 3 );

		assert rev1.getReference().equals( ed1 );
		assert rev2.getReference().equals( ed2 );
		assert rev3.getReference().equals( ed2 );
	}
