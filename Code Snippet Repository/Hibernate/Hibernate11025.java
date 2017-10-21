	@Test
	public void testHistoryOfEdId1() {
		BiEmbIdRefIngEntity ing1 = getEntityManager().find( BiEmbIdRefIngEntity.class, ing1_id );

		BiEmbIdRefEdEntity rev1 = getAuditReader().find( BiEmbIdRefEdEntity.class, ed1_id, 1 );
		BiEmbIdRefEdEntity rev2 = getAuditReader().find( BiEmbIdRefEdEntity.class, ed1_id, 2 );

		assert rev1.getReferencing().equals( ing1 );
		assert rev2.getReferencing() == null;
	}
