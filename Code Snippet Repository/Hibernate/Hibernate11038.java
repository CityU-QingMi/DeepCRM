	@Test
	public void testRevisionsCounts() {
		Assert.assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( Person.class, personId ) );
		Assert.assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( Account.class, accountId ) );
		Assert.assertEquals(
				Arrays.asList( 2 ), getAuditReader().getRevisions(
				AccountNotAuditedOwners.class,
				accountNotAuditedOwnersId
		)
		);
	}
