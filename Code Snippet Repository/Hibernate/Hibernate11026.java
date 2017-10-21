	@Test
	public void testHistoryOfEdId2() {
		BiEmbIdRefIngEntity ing1 = getEntityManager().find( BiEmbIdRefIngEntity.class, ing1_id );

		BiEmbIdRefEdEntity rev1 = getAuditReader().find( BiEmbIdRefEdEntity.class, ed2_id, 1 );
		BiEmbIdRefEdEntity rev2 = getAuditReader().find( BiEmbIdRefEdEntity.class, ed2_id, 2 );

		assert rev1.getReferencing() == null;
		assert rev2.getReferencing().equals( ing1 );
	}
