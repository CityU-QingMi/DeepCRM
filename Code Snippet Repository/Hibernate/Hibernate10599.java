	@Test
	public void testHistoryOfId2() {
		EmbIdTestEntity ver1 = new EmbIdTestEntity( id2, "y" );
		EmbIdTestEntity ver2 = new EmbIdTestEntity( id2, "y2" );
		EmbIdTestEntity ver3 = new EmbIdTestEntity( id2, "y3" );

		assert getAuditReader().find( EmbIdTestEntity.class, id2, 1 ) == null;
		assert getAuditReader().find( EmbIdTestEntity.class, id2, 2 ).equals( ver1 );
		assert getAuditReader().find( EmbIdTestEntity.class, id2, 3 ).equals( ver2 );
		assert getAuditReader().find( EmbIdTestEntity.class, id2, 4 ).equals( ver3 );
		assert getAuditReader().find( EmbIdTestEntity.class, id2, 5 ) == null;
	}
