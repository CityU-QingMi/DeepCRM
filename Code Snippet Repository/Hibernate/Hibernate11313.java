	@Test
	public void testImplicitTransitiveAuditParents() {
		// expectedChild.notAudited shall be null, because it is not audited.
		ImplicitTransitiveChildEntity expectedChild = new ImplicitTransitiveChildEntity(
				childImpTransId,
				"grandparent 1",
				null,
				"parent 1",
				"child 1"
		);
		ImplicitTransitiveChildEntity child = getAuditReader().find(
				ImplicitTransitiveChildEntity.class,
				childImpTransId,
				1
		);
		Assert.assertEquals( expectedChild, child );
	}
