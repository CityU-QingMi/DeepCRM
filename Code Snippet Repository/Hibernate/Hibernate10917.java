	@Test
	public void testHistoryOfEdIng1() {
		SetRefEdMulIdEntity ed1 = getEntityManager().find( SetRefEdMulIdEntity.class, ed1_id );
		SetRefEdMulIdEntity ed2 = getEntityManager().find( SetRefEdMulIdEntity.class, ed2_id );

		SetRefIngMulIdEntity rev1 = getAuditReader().find( SetRefIngMulIdEntity.class, ing1_id, 1 );
		SetRefIngMulIdEntity rev2 = getAuditReader().find( SetRefIngMulIdEntity.class, ing1_id, 2 );
		SetRefIngMulIdEntity rev3 = getAuditReader().find( SetRefIngMulIdEntity.class, ing1_id, 3 );

		assert rev1.getReference().equals( ed1 );
		assert rev2.getReference().equals( ed2 );
		assert rev3.getReference().equals( ed2 );
	}
