	@Test
	public void testHistoryOfId1() {
		EmbIdTestEntity ver1 = new EmbIdTestEntity( id1, "x" );
		EmbIdTestEntity ver2 = new EmbIdTestEntity( id1, "x2" );

		assert getAuditReader().find( EmbIdTestEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( EmbIdTestEntity.class, id1, 2 ).equals( ver1 );
		assert getAuditReader().find( EmbIdTestEntity.class, id1, 3 ).equals( ver2 );
		assert getAuditReader().find( EmbIdTestEntity.class, id1, 4 ) == null;
		assert getAuditReader().find( EmbIdTestEntity.class, id1, 5 ) == null;
	}
