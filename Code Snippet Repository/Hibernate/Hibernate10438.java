	@Test
	public void testHistoryOfSse1() {
		StringSetEntity rev1 = getAuditReader().find( StringSetEntity.class, sse1_id, 1 );
		StringSetEntity rev2 = getAuditReader().find( StringSetEntity.class, sse1_id, 2 );
		StringSetEntity rev3 = getAuditReader().find( StringSetEntity.class, sse1_id, 3 );

		assert rev1.getStrings().equals( Collections.EMPTY_SET );
		assert rev2.getStrings().equals( TestTools.makeSet( "sse1_string1", "sse1_string2" ) );
		assert rev3.getStrings().equals( TestTools.makeSet( "sse1_string1", "sse1_string2" ) );
	}
