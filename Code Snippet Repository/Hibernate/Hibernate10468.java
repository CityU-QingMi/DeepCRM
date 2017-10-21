	@Test
	public void testHistoryOfId4() {
		ComponentTestEntity ver1 = new ComponentTestEntity( id4, null, null );
		ComponentTestEntity ver2 = new ComponentTestEntity( id4, new Component1( "n", null ), null );
		ComponentTestEntity ver3 = new ComponentTestEntity( id4, null, null );

		assert getAuditReader().find( ComponentTestEntity.class, id4, 1 ).equals( ver1 );
		assert getAuditReader().find( ComponentTestEntity.class, id4, 2 ).equals( ver2 );
		assert getAuditReader().find( ComponentTestEntity.class, id4, 3 ).equals( ver3 );
		assert getAuditReader().find( ComponentTestEntity.class, id4, 4 ).equals( ver3 );
	}
