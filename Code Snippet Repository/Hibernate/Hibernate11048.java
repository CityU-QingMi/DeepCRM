	@Test
	public void testHistoryOfIngId2() {
		UniRefEdEntity ed3 = getEntityManager().find( UniRefEdEntity.class, ed3_id );
		UniRefEdEntity ed4 = getEntityManager().find( UniRefEdEntity.class, ed4_id );

		UniRefIngEntity rev1 = getAuditReader().find( UniRefIngEntity.class, ing2_id, 1 );
		UniRefIngEntity rev2 = getAuditReader().find( UniRefIngEntity.class, ing2_id, 2 );
		UniRefIngEntity rev3 = getAuditReader().find( UniRefIngEntity.class, ing2_id, 3 );

		assert rev1.getReference().equals( ed3 );
		assert rev2.getReference().equals( ed3 );
		assert rev3.getReference().equals( ed4 );
	}
