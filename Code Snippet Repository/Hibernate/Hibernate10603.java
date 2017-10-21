	@Test
	public void testHistoryOfId6() {
		EmbIdWithCustomTypeTestEntity ver1 = new EmbIdWithCustomTypeTestEntity( id6, "d" );
		EmbIdWithCustomTypeTestEntity ver2 = new EmbIdWithCustomTypeTestEntity( id6, "d2" );

		assert getAuditReader().find( EmbIdWithCustomTypeTestEntity.class, id6, 1 ) == null;
		assert getAuditReader().find( EmbIdWithCustomTypeTestEntity.class, id6, 2 ).equals( ver1 );
		assert getAuditReader().find( EmbIdWithCustomTypeTestEntity.class, id6, 3 ).equals( ver2 );
		assert getAuditReader().find( EmbIdWithCustomTypeTestEntity.class, id6, 4 ) == null;
		assert getAuditReader().find( EmbIdWithCustomTypeTestEntity.class, id6, 5 ) == null;
	}
