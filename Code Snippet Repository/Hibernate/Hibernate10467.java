	@Test
	public void testHistoryOfId3() {
		ComponentTestEntity ver1 = new ComponentTestEntity( id3, new Component1( "a3", "b3" ), null );
		ComponentTestEntity ver2 = new ComponentTestEntity( id3, new Component1( "a3", "b3'" ), null );

		assert getAuditReader().find( ComponentTestEntity.class, id3, 1 ).equals( ver1 );
		assert getAuditReader().find( ComponentTestEntity.class, id3, 2 ).equals( ver1 );
		assert getAuditReader().find( ComponentTestEntity.class, id3, 3 ).equals( ver2 );
		assert getAuditReader().find( ComponentTestEntity.class, id3, 4 ).equals( ver2 );
	}
