	@Test
	public void testHistoryOfEdId2() {
		BiRefIngEntity ing1 = getEntityManager().find( BiRefIngEntity.class, ing1_id );

		BiRefEdEntity rev1 = getAuditReader().find( BiRefEdEntity.class, ed2_id, 1 );
		BiRefEdEntity rev2 = getAuditReader().find( BiRefEdEntity.class, ed2_id, 2 );

		assert rev1.getReferencing() == null;
		assert rev2.getReferencing().equals( ing1 );
	}
