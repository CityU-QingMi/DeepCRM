	@Test
	public void testHistoryOfChildId1() {
		ChildEntity ver1 = new ChildEntity( id1, "x", 1l );
		ChildEntity ver2 = new ChildEntity( id1, "y", 2l );

		assert getAuditReader().find( ChildEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( ChildEntity.class, id1, 2 ).equals( ver2 );

		assert getAuditReader().find( ParentEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( ParentEntity.class, id1, 2 ).equals( ver2 );
	}
