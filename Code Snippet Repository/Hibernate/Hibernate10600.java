	@Test
	public void testHistoryOfId3() {
		MulIdTestEntity ver1 = new MulIdTestEntity( id3.getId1(), id3.getId2(), "a" );
		MulIdTestEntity ver2 = new MulIdTestEntity( id3.getId1(), id3.getId2(), "a2" );

		assert getAuditReader().find( MulIdTestEntity.class, id3, 1 ).equals( ver1 );
		assert getAuditReader().find( MulIdTestEntity.class, id3, 2 ).equals( ver1 );
		assert getAuditReader().find( MulIdTestEntity.class, id3, 3 ).equals( ver2 );
		assert getAuditReader().find( MulIdTestEntity.class, id3, 4 ) == null;
		assert getAuditReader().find( MulIdTestEntity.class, id3, 5 ) == null;
	}
