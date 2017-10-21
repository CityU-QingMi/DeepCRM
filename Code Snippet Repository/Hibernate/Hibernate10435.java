	@Test
	public void testHistoryOfSse2() {
		StringMapEntity rev1 = getAuditReader().find( StringMapEntity.class, sme2_id, 1 );
		StringMapEntity rev2 = getAuditReader().find( StringMapEntity.class, sme2_id, 2 );
		StringMapEntity rev3 = getAuditReader().find( StringMapEntity.class, sme2_id, 3 );
		StringMapEntity rev4 = getAuditReader().find( StringMapEntity.class, sme2_id, 4 );

		assert rev1.getStrings().equals( TestTools.makeMap( "1", "a" ) );
		assert rev2.getStrings().equals( TestTools.makeMap( "1", "a" ) );
		assert rev3.getStrings().equals( TestTools.makeMap( "1", "b" ) );
		assert rev4.getStrings().equals( TestTools.makeMap( "1", "b" ) );
	}
