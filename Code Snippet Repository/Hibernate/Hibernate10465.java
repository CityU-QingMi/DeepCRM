	@Test
	public void testHistoryOfId1() {
		ComponentTestEntity ver1 = new ComponentTestEntity( id1, new Component1( "a", "b" ), null );
		ComponentTestEntity ver2 = new ComponentTestEntity( id1, new Component1( "a'", "b'" ), null );

		assert getAuditReader().find( ComponentTestEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( ComponentTestEntity.class, id1, 2 ).equals( ver2 );
		assert getAuditReader().find( ComponentTestEntity.class, id1, 3 ).equals( ver2 );
		assert getAuditReader().find( ComponentTestEntity.class, id1, 4 ).equals( ver2 );
	}
