	@Test
	public void testHistoryOfEdId1() {
		BiMulIdRefIngEntity ing1 = getEntityManager().find( BiMulIdRefIngEntity.class, ing1_id );

		BiMulIdRefEdEntity rev1 = getAuditReader().find( BiMulIdRefEdEntity.class, ed1_id, 1 );
		BiMulIdRefEdEntity rev2 = getAuditReader().find( BiMulIdRefEdEntity.class, ed1_id, 2 );

		assert rev1.getReferencing().equals( ing1 );
		assert rev2.getReferencing() == null;
	}
