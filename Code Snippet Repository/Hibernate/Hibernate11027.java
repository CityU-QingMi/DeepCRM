	@Test
	public void testHistoryOfIngId1() {
		BiEmbIdRefEdEntity ed1 = getEntityManager().find( BiEmbIdRefEdEntity.class, ed1_id );
		BiEmbIdRefEdEntity ed2 = getEntityManager().find( BiEmbIdRefEdEntity.class, ed2_id );

		BiEmbIdRefIngEntity rev1 = getAuditReader().find( BiEmbIdRefIngEntity.class, ing1_id, 1 );
		BiEmbIdRefIngEntity rev2 = getAuditReader().find( BiEmbIdRefIngEntity.class, ing1_id, 2 );

		assert rev1.getReference().equals( ed1 );
		assert rev2.getReference().equals( ed2 );
	}
