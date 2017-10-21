	@Test
	public void testPolymorphicQuery() {
		Assert.assertEquals(
				childVer1, getAuditReader().createQuery()
				.forEntitiesAtRevision( ChildEntity.class, 1 )
				.getSingleResult()
		);
		Assert.assertEquals(
				childVer1, getAuditReader().createQuery()
				.forEntitiesAtRevision( ParentEntity.class, 1 )
				.getSingleResult()
		);

		List childEntityRevisions = getAuditReader().createQuery().forRevisionsOfEntity(
				ChildEntity.class,
				true,
				false
		).getResultList();
		Assert.assertEquals( Arrays.asList( childVer1, childVer2 ), childEntityRevisions );

		List parentEntityRevisions = getAuditReader().createQuery().forRevisionsOfEntity(
				ParentEntity.class,
				true,
				false
		).getResultList();
		Assert.assertEquals( Arrays.asList( childVer1, parentVer1, childVer2, parentVer2 ), parentEntityRevisions );
	}
