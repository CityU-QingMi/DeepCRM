	@Test
	public void testHistoryOfEdId2() {
		BiMulIdRefIngEntity ing1 = getEntityManager().find( BiMulIdRefIngEntity.class, ing1_id );

		BiMulIdRefEdEntity rev1 = getAuditReader().find( BiMulIdRefEdEntity.class, ed2_id, 1 );
		BiMulIdRefEdEntity rev2 = getAuditReader().find( BiMulIdRefEdEntity.class, ed2_id, 2 );

		assert rev1.getReferencing() == null;
		assert rev2.getReferencing().equals( ing1 );
	}
