	@Test
	public void testHistoryOfEd() {
		RefIngMapKeyEntity ing1 = getEntityManager().find( RefIngMapKeyEntity.class, ing1_id );
		RefIngMapKeyEntity ing2 = getEntityManager().find( RefIngMapKeyEntity.class, ing2_id );

		RefEdMapKeyEntity rev1 = getAuditReader().find( RefEdMapKeyEntity.class, ed_id, 1 );
		RefEdMapKeyEntity rev2 = getAuditReader().find( RefEdMapKeyEntity.class, ed_id, 2 );

		assert rev1.getIdmap().equals( TestTools.makeMap( "a", ing1 ) );
		assert rev2.getIdmap().equals( TestTools.makeMap( "a", ing1, "b", ing2 ) );
	}
