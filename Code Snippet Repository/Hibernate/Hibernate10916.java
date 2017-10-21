	@Test
	public void testHistoryOfEdId2() {
		SetRefIngMulIdEntity ing1 = getEntityManager().find( SetRefIngMulIdEntity.class, ing1_id );
		SetRefIngMulIdEntity ing2 = getEntityManager().find( SetRefIngMulIdEntity.class, ing2_id );

		SetRefEdMulIdEntity rev1 = getAuditReader().find( SetRefEdMulIdEntity.class, ed2_id, 1 );
		SetRefEdMulIdEntity rev2 = getAuditReader().find( SetRefEdMulIdEntity.class, ed2_id, 2 );
		SetRefEdMulIdEntity rev3 = getAuditReader().find( SetRefEdMulIdEntity.class, ed2_id, 3 );

		assert rev1.getReffering().equals( Collections.EMPTY_SET );
		assert rev2.getReffering().equals( makeSet( ing1 ) );
		assert rev3.getReffering().equals( makeSet( ing1, ing2 ) );
	}
