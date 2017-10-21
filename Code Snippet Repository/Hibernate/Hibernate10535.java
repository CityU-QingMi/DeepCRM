	@Test
	@Priority(8)
	public void testRevisionCount() {
		Assert.assertEquals(
				Arrays.asList( 1, 2 ), getAuditReader().getRevisions(
				UnspecifiedEnumTypeEntity.class,
				id
		)
		);
	}
