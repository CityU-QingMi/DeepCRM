	@Test
	public void testHistoryOfIngId1() {
		BiMulIdRefEdEntity ed1 = getEntityManager().find( BiMulIdRefEdEntity.class, ed1_id );
		BiMulIdRefEdEntity ed2 = getEntityManager().find( BiMulIdRefEdEntity.class, ed2_id );

		BiMulIdRefIngEntity rev1 = getAuditReader().find( BiMulIdRefIngEntity.class, ing1_id, 1 );
		BiMulIdRefIngEntity rev2 = getAuditReader().find( BiMulIdRefIngEntity.class, ing1_id, 2 );

		assert rev1.getReference().equals( ed1 );
		assert rev2.getReference().equals( ed2 );
	}
