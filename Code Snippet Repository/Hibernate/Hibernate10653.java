	@Test
	public void testRevisionsCounts() {
		Assert.assertEquals(
				Arrays.asList( 1, 3 ), getAuditReader().getRevisions(
				ChildEntity.class,
				childVer1.getId()
		)
		);
		Assert.assertEquals(
				Arrays.asList( 2, 4 ), getAuditReader().getRevisions(
				ParentEntity.class,
				parentVer1.getId()
		)
		);
	}
