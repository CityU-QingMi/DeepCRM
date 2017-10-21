	@Test
	public void testHistoryOfId2() {
		BasicTestEntity1 ver1 = new BasicTestEntity1( id2, null, 20 );
		BasicTestEntity1 ver2 = new BasicTestEntity1( id2, "y2", 20 );

		assert getAuditReader().find( BasicTestEntity1.class, id2, 1 ) == null;
		assert getAuditReader().find( BasicTestEntity1.class, id2, 2 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id2, 3 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id2, 4 ).equals( ver2 );
	}
