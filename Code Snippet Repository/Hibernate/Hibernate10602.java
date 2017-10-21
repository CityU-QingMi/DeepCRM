	@Test
	public void testHistoryOfId5() {
		EmbIdWithCustomTypeTestEntity ver1 = new EmbIdWithCustomTypeTestEntity( id5, "c" );
		EmbIdWithCustomTypeTestEntity ver2 = new EmbIdWithCustomTypeTestEntity( id5, "c2" );
		EmbIdWithCustomTypeTestEntity ver3 = new EmbIdWithCustomTypeTestEntity( id5, "c3" );

		assert getAuditReader().find( EmbIdWithCustomTypeTestEntity.class, id5, 1 ).equals( ver1 );
		assert getAuditReader().find( EmbIdWithCustomTypeTestEntity.class, id5, 2 ).equals( ver1 );
		assert getAuditReader().find( EmbIdWithCustomTypeTestEntity.class, id5, 3 ).equals( ver2 );
		assert getAuditReader().find( EmbIdWithCustomTypeTestEntity.class, id5, 4 ).equals( ver3 );
		assert getAuditReader().find( EmbIdWithCustomTypeTestEntity.class, id5, 5 ).equals( ver3 );
	}
