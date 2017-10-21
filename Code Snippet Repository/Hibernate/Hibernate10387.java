	@Test
	public void testHistoryOfId2() {
		BasicTestEntity1 ver1 = new BasicTestEntity1( id2, "y", 20 );
		BasicTestEntity1 ver2 = new BasicTestEntity1( id2, "y", 21 );
		BasicTestEntity1 ver3 = new BasicTestEntity1( id2, "y3", 22 );

		assert getAuditReader().find( BasicTestEntity1.class, id2, 1 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id2, 2 ).equals( ver2 );
		assert getAuditReader().find( BasicTestEntity1.class, id2, 3 ).equals( ver3 );
	}
