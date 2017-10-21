	@Test
	public void testHistoryOfChildId1() {
		ChildPrimaryKeyJoinEntity ver1 = new ChildPrimaryKeyJoinEntity( id1, "x", 1l );
		ChildPrimaryKeyJoinEntity ver2 = new ChildPrimaryKeyJoinEntity( id1, "y", 2l );

		assert getAuditReader().find( ChildPrimaryKeyJoinEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( ChildPrimaryKeyJoinEntity.class, id1, 2 ).equals( ver2 );

		assert getAuditReader().find( ParentEntity.class, id1, 1 ).equals( ver1 );
		assert getAuditReader().find( ParentEntity.class, id1, 2 ).equals( ver2 );
	}
