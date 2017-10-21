	@Test
	public void testPolymorphicQuery() {
		EmptyChildEntity childVer1 = new EmptyChildEntity( id1, "x" );

		assert getAuditReader().createQuery().forEntitiesAtRevision( EmptyChildEntity.class, 1 ).getSingleResult()
				.equals( childVer1 );

		assert getAuditReader().createQuery().forEntitiesAtRevision( ParentEntity.class, 1 ).getSingleResult()
				.equals( childVer1 );
	}
