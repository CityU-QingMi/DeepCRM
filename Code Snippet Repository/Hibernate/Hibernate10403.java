	@Test
	public void testHistoryOfId3() {
		BasicTestEntity1 ver1 = new BasicTestEntity1( id3, "z", 30 );

		assert getAuditReader().find( BasicTestEntity1.class, id3, 1 ) == null;
		assert getAuditReader().find( BasicTestEntity1.class, id3, 2 ) == null;
		assert getAuditReader().find( BasicTestEntity1.class, id3, 3 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id3, 4 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id3, 5 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id3, 6 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id3, 7 ).equals( ver1 );
	}
