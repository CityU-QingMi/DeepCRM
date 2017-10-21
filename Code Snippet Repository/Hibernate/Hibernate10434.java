	@Test
	public void testHistoryOfSse1() {
		StringMapEntity rev1 = getAuditReader().find( StringMapEntity.class, sme1_id, 1 );
		StringMapEntity rev2 = getAuditReader().find( StringMapEntity.class, sme1_id, 2 );
		StringMapEntity rev3 = getAuditReader().find( StringMapEntity.class, sme1_id, 3 );
		StringMapEntity rev4 = getAuditReader().find( StringMapEntity.class, sme1_id, 4 );

		assert rev1.getStrings().equals( Collections.EMPTY_MAP );
		assert rev2.getStrings().equals( TestTools.makeMap( "1", "a", "2", "b" ) );
		assert rev3.getStrings().equals( TestTools.makeMap( "2", "b" ) );
		assert rev4.getStrings().equals( TestTools.makeMap( "2", "b" ) );
	}
