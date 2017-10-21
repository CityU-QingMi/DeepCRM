	@Test
	public void testHistoryOfIngId2() {
		UniRefEdEntity ed2 = getEntityManager().find( UniRefEdEntity.class, ed2_id );

		UniRefIngEntity rev1 = getAuditReader().find( UniRefIngEntity.class, ing2_id, 1 );
		UniRefIngEntity rev2 = getAuditReader().find( UniRefIngEntity.class, ing2_id, 2 );
		UniRefIngEntity rev3 = getAuditReader().find( UniRefIngEntity.class, ing2_id, 3 );

		assert rev1.getReference() == null;
		assert rev2.getReference() == null;
		assert rev3.getReference().equals( ed2 );
	}
