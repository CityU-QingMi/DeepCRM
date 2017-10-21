	@Test
	public void testPolymorphicQuery() {
		ChildEntity childVer1 = new ChildEntity( id1, "x", 1l );

		assert getAuditReader().createQuery().forEntitiesAtRevision( ChildEntity.class, 1 ).getSingleResult()
				.equals( childVer1 );

		assert getAuditReader().createQuery().forEntitiesAtRevision( ParentEntity.class, 1 ).getSingleResult()
				.equals( childVer1 );
	}
