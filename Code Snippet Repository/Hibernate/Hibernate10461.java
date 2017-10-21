	@Test
	public void testHistoryOfImke() {
		StrTestEntity ste1 = getEntityManager().find( StrTestEntity.class, ste1_id );
		StrTestEntity ste2 = getEntityManager().find( StrTestEntity.class, ste2_id );

		IdMapKeyEntity rev1 = getAuditReader().find( IdMapKeyEntity.class, imke_id, 1 );
		IdMapKeyEntity rev2 = getAuditReader().find( IdMapKeyEntity.class, imke_id, 2 );

		assert rev1.getIdmap().equals( TestTools.makeMap( ste1.getId(), ste1 ) );
		assert rev2.getIdmap().equals( TestTools.makeMap( ste1.getId(), ste1, ste2.getId(), ste2 ) );
	}
