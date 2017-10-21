	@Test
	public void testHistoryOfSse2() {
		StringListEntity rev1 = getAuditReader().find( StringListEntity.class, sle2_id, 1 );
		StringListEntity rev2 = getAuditReader().find( StringListEntity.class, sle2_id, 2 );
		StringListEntity rev3 = getAuditReader().find( StringListEntity.class, sle2_id, 3 );

		assert rev1.getStrings().equals( TestTools.makeList( "sle2_string1", "sle2_string2" ) );
		assert rev2.getStrings().equals( TestTools.makeList( "sle2_string1", "sle2_string2", "sle2_string1" ) );
		assert rev3.getStrings().equals( TestTools.makeList( "sle2_string2", "sle2_string1" ) );
	}
