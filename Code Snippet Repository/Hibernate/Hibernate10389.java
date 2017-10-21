	@Test
	public void testHistoryOfId1() {
		BasicTestEntity1 ver1 = new BasicTestEntity1( id1, "x", 1 );
		BasicTestEntity1 ver2 = new BasicTestEntity1( id1, null, 1 );

		assert getAuditReader().find( BasicTestEntity1.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id1, 2 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id1, 3 ).equals( ver2 );
		assert getAuditReader().find( BasicTestEntity1.class, id1, 4 ).equals( ver2 );
	}
