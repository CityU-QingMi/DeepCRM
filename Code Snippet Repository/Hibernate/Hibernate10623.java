	@Test
	public void testHistoryOfChildId1() {
		ChildEntity ver1 = new ChildEntity( id1, "x", null );
		ChildEntity ver2 = new ChildEntity( id1, null, 2l );

		assert getAuditReader().find( ChildEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( ChildEntity.class, id1, 2 ).equals( ver2 );

		assert getAuditReader().find( ParentEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( ParentEntity.class, id1, 2 ).equals( ver2 );
	}
