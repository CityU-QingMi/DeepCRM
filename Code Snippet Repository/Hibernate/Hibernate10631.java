	@Test
	public void testPolymorphicQuery() {
		ChildPrimaryKeyJoinEntity childVer1 = new ChildPrimaryKeyJoinEntity( id1, "x", 1l );

		assert getAuditReader().createQuery()
				.forEntitiesAtRevision( ChildPrimaryKeyJoinEntity.class, 1 )
				.getSingleResult()
				.equals( childVer1 );

		assert getAuditReader().createQuery().forEntitiesAtRevision( ParentEntity.class, 1 ).getSingleResult()
				.equals( childVer1 );
	}
