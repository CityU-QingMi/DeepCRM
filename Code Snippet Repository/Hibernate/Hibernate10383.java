	@Test
	public void testHistoryOfId1() {
		BasicTestEntity2 ver1 = new BasicTestEntity2( id1, "x", null );
		BasicTestEntity2 ver2 = new BasicTestEntity2( id1, "x2", null );

		assert getAuditReader().find( BasicTestEntity2.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( BasicTestEntity2.class, id1, 2 ).equals( ver2 );
		assert getAuditReader().find( BasicTestEntity2.class, id1, 3 ).equals( ver2 );
		assert getAuditReader().find( BasicTestEntity2.class, id1, 4 ) == null;
	}
