	@Test
	public void testRevisionsCounts() {
		Assert.assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( Person.class, expLukaszRev1.getId() ) );
		Assert.assertEquals(
				Arrays.asList( 1 ), getAuditReader().getRevisions(
				RightsSubject.class,
				expLukaszRev1.getId()
		)
		);

		Assert.assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( Role.class, expAdminRev1.getId() ) );
		Assert.assertEquals(
				Arrays.asList( 1 ), getAuditReader().getRevisions(
				RightsSubject.class,
				expAdminRev1.getId()
		)
		);
	}
