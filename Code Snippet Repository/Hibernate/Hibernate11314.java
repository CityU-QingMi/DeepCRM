	@Test
	public void testExplicitTransitiveAuditParents() {
		// expectedChild.notAudited shall be null, because it is not audited.
		ExplicitTransitiveChildEntity expectedChild = new ExplicitTransitiveChildEntity(
				childExpTransId,
				"grandparent 2",
				null,
				"parent 2",
				"child 2"
		);
		ExplicitTransitiveChildEntity child = getAuditReader().find(
				ExplicitTransitiveChildEntity.class,
				childExpTransId,
				2
		);
		Assert.assertEquals( expectedChild, child );
	}
