	@Test
	public void testRevisionsCounts() {
		Assert.assertEquals(
				Arrays.asList( 1, 2, 3, 4, 5 ), getAuditReader().getRevisions(
				JoinTableEntity.class,
				collectionEntityId
		)
		);
		Assert.assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( StrTestEntity.class, element1Id ) );
		Assert.assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( StrTestEntity.class, element2Id ) );
	}
