	@Test
	public void testHistoryOfId1() {
		StrTestEntity ver1 = new StrTestEntity( "x", id );
		StrTestEntity ver2 = new StrTestEntity( "y", id );
		StrTestEntity ver3 = new StrTestEntity( "z", id );

		assert getAuditReader().find( StrTestEntity.class, id, 1 ).equals( ver1 );
		assert getAuditReader().find( StrTestEntity.class, id, 2 ).equals( ver2 );
		assert getAuditReader().find( StrTestEntity.class, id, 3 ).equals( ver2 );
		assert getAuditReader().find( StrTestEntity.class, id, 4 ).equals( ver3 );
	}
