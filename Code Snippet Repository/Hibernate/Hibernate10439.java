	@Test
	public void testHistoryOfSse2() {
		StringSetEntity rev1 = getAuditReader().find( StringSetEntity.class, sse2_id, 1 );
		StringSetEntity rev2 = getAuditReader().find( StringSetEntity.class, sse2_id, 2 );
		StringSetEntity rev3 = getAuditReader().find( StringSetEntity.class, sse2_id, 3 );

		assert rev1.getStrings().equals( TestTools.makeSet( "sse2_string1", "sse2_string2" ) );
		assert rev2.getStrings().equals( TestTools.makeSet( "sse2_string1", "sse2_string2" ) );
		assert rev3.getStrings().equals( TestTools.makeSet( "sse2_string2" ) );
	}
