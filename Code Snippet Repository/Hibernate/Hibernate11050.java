	@Test
	public void testHistoryOfIngId1() {
		UniRefEdEntity ed1 = getEntityManager().find( UniRefEdEntity.class, ed1_id );

		UniRefIngEntity rev1 = getAuditReader().find( UniRefIngEntity.class, ing1_id, 1 );
		UniRefIngEntity rev2 = getAuditReader().find( UniRefIngEntity.class, ing1_id, 2 );
		UniRefIngEntity rev3 = getAuditReader().find( UniRefIngEntity.class, ing1_id, 3 );

		assert rev1.getReference().equals( ed1 );
		assert rev2.getReference() == null;
		assert rev3.getReference() == null;
	}
