	@Test
	public void testHistoryOfGivenIdStrEntity() {
		Assert.assertEquals( new GivenIdStrEntity( 1, "data" ), getAuditReader().find( GivenIdStrEntity.class, 1, 1 ) );
		Assert.assertEquals(
				new GivenIdStrEntity( 1, "modified data" ), getAuditReader().find(
				GivenIdStrEntity.class,
				1,
				3
		)
		);
	}
