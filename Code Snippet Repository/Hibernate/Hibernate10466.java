	@Test
	public void testHistoryOfId2() {
		ComponentTestEntity ver1 = new ComponentTestEntity( id2, new Component1( "a2", "b2" ), null );
		ComponentTestEntity ver2 = new ComponentTestEntity( id2, new Component1( "a2'", "b2" ), null );

		assert getAuditReader().find( ComponentTestEntity.class, id2, 1 ).equals( ver1 );
		assert getAuditReader().find( ComponentTestEntity.class, id2, 2 ).equals( ver2 );
		assert getAuditReader().find( ComponentTestEntity.class, id2, 3 ).equals( ver2 );
		assert getAuditReader().find( ComponentTestEntity.class, id2, 4 ) == null;
	}
