	@Test
	public void testHistoryOfId1() {
		IntTestEntity ver1 = new IntTestEntity( 10, id1 );
		IntTestEntity ver2 = new IntTestEntity( 20, id1 );

		List<Number> revisions = getAuditReader().getRevisions(
				IntTestEntity.class, id1
		);

		Assert.assertEquals(
				ver1, getAuditReader().find(
				IntTestEntity.class, id1, revisions.get( 0 )
		)
		);
		Assert.assertEquals(
				ver2, getAuditReader().find(
				IntTestEntity.class, id1, revisions.get( 1 )
		)
		);
	}
