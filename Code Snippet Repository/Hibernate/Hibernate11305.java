	@Test
	public void testSingleAuditParent() {
		// expectedSingleChild.parent, expectedSingleChild.relation and expectedSingleChild.notAudited shall be null, because they are not audited.
		ChildSingleParentEntity expectedSingleChild = new ChildSingleParentEntity(
				childSingleId,
				"grandparent 1",
				null,
				null,
				"child 1",
				null
		);
		ChildSingleParentEntity child = getAuditReader().find( ChildSingleParentEntity.class, childSingleId, 1 );
		Assert.assertEquals( expectedSingleChild, child );
		Assert.assertNull( child.getRelation() );
	}
