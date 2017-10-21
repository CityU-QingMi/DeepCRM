	@Test
	public void testHistoryOfImke() {
		ComponentTestEntity cte1 = getEntityManager().find( ComponentTestEntity.class, cte1_id );
		ComponentTestEntity cte2 = getEntityManager().find( ComponentTestEntity.class, cte2_id );

		// These fields are unversioned.
		cte1.setComp2( null );
		cte2.setComp2( null );

		ComponentMapKeyEntity rev1 = getAuditReader().find( ComponentMapKeyEntity.class, cmke_id, 1 );
		ComponentMapKeyEntity rev2 = getAuditReader().find( ComponentMapKeyEntity.class, cmke_id, 2 );

		assert rev1.getIdmap().equals( TestTools.makeMap( cte1.getComp1(), cte1 ) );
		assert rev2.getIdmap().equals( TestTools.makeMap( cte1.getComp1(), cte1, cte2.getComp1(), cte2 ) );
	}
