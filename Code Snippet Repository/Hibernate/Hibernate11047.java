	@Test
	public void testHistoryOfIngId1() {
		UniRefEdEntity ed1 = getEntityManager().find( UniRefEdEntity.class, ed1_id );
		UniRefEdEntity ed2 = getEntityManager().find( UniRefEdEntity.class, ed2_id );

		UniRefIngEntity rev1 = getAuditReader().find( UniRefIngEntity.class, ing1_id, 1 );
		UniRefIngEntity rev2 = getAuditReader().find( UniRefIngEntity.class, ing1_id, 2 );
		UniRefIngEntity rev3 = getAuditReader().find( UniRefIngEntity.class, ing1_id, 3 );

		assert rev1.getReference().equals( ed1 );
		assert rev2.getReference().equals( ed2 );
		assert rev3.getReference().equals( ed2 );
	}
