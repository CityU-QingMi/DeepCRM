	@Test
	public void testHistoryOfSle1() {
		StringListEntity rev1 = getAuditReader().find( StringListEntity.class, sle1_id, 1 );
		StringListEntity rev2 = getAuditReader().find( StringListEntity.class, sle1_id, 2 );
		StringListEntity rev3 = getAuditReader().find( StringListEntity.class, sle1_id, 3 );

		assert rev1.getStrings().equals( Collections.EMPTY_LIST );
		assert rev2.getStrings().equals( TestTools.makeList( "sle1_string1", "sle1_string2" ) );
		assert rev3.getStrings().equals( TestTools.makeList( "sle1_string3", "sle1_string2" ) );
	}
