	@Test
	public void testHistoryOfEdId1() {
		BiRefIngEntity ing1 = getEntityManager().find( BiRefIngEntity.class, ing1_id );
		BiRefIngEntity ing2 = getEntityManager().find( BiRefIngEntity.class, ing2_id );

		BiRefEdEntity rev1 = getAuditReader().find( BiRefEdEntity.class, ed1_id, 1 );
		BiRefEdEntity rev2 = getAuditReader().find( BiRefEdEntity.class, ed1_id, 2 );
		BiRefEdEntity rev3 = getAuditReader().find( BiRefEdEntity.class, ed1_id, 3 );
		BiRefEdEntity rev4 = getAuditReader().find( BiRefEdEntity.class, ed1_id, 4 );

		assert rev1.getReferencing() == null;
		assert rev2.getReferencing().equals( ing1 );
		assert rev3.getReferencing().equals( ing2 );
		assert rev4.getReferencing() == null;
	}
