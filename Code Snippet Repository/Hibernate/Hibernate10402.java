	@Test
	public void testHistoryOfId2() {
		BasicTestEntity1 ver1 = new BasicTestEntity1( id2, "y", 20 );
		BasicTestEntity1 ver2 = new BasicTestEntity1( id2, "y2", 20 );
		BasicTestEntity1 ver3 = new BasicTestEntity1( id2, "y3", 21 );

		assert getAuditReader().find( BasicTestEntity1.class, id2, 1 ) == null;
		assert getAuditReader().find( BasicTestEntity1.class, id2, 2 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id2, 3 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id2, 4 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity1.class, id2, 5 ).equals( ver2 );
		assert getAuditReader().find( BasicTestEntity1.class, id2, 6 ).equals( ver2 );
		assert getAuditReader().find( BasicTestEntity1.class, id2, 7 ).equals( ver3 );
	}
