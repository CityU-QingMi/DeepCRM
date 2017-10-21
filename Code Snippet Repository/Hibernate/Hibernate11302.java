	@Test
	public void testMultipleAuditParents() {
		// expectedMultipleChild.notAudited shall be null, because it is not audited.
		ChildMultipleParentsEntity expectedMultipleChild = new ChildMultipleParentsEntity(
				childMultipleId,
				"grandparent 1",
				null,
				"parent 1",
				"child 1",
				new StrIntTestEntity(
						"data 1",
						1,
						siteMultipleId
				)
		);
		ChildMultipleParentsEntity child = getAuditReader().find(
				ChildMultipleParentsEntity.class,
				childMultipleId,
				1
		);
		Assert.assertEquals( expectedMultipleChild, child );
		Assert.assertEquals( expectedMultipleChild.getRelation().getId(), child.getRelation().getId() );
	}
