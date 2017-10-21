	@Test
	public void testHistoryOfId4() {
		MulIdTestEntity ver1 = new MulIdTestEntity( id4.getId1(), id4.getId2(), "b" );
		MulIdTestEntity ver2 = new MulIdTestEntity( id4.getId1(), id4.getId2(), "b2" );

		assert getAuditReader().find( MulIdTestEntity.class, id4, 1 ) == null;
		assert getAuditReader().find( MulIdTestEntity.class, id4, 2 ).equals( ver1 );
		assert getAuditReader().find( MulIdTestEntity.class, id4, 3 ).equals( ver2 );
		assert getAuditReader().find( MulIdTestEntity.class, id4, 4 ).equals( ver2 );
		assert getAuditReader().find( MulIdTestEntity.class, id4, 5 ).equals( ver2 );
	}
