	@Test
	public void testHistoryOfChildId1() {
		EmptyChildEntity ver1 = new EmptyChildEntity( id1, "x" );
		EmptyChildEntity ver2 = new EmptyChildEntity( id1, "y" );

		assert getAuditReader().find( EmptyChildEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( EmptyChildEntity.class, id1, 2 ).equals( ver2 );

		assert getAuditReader().find( ParentEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( ParentEntity.class, id1, 2 ).equals( ver2 );
	}
