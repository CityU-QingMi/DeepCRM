	@Test
	public void testCompleteAuditParents() {
		// expectedBaby.notAudited shall be null, because it is not audited.
		BabyCompleteEntity expectedBaby = new BabyCompleteEntity(
				babyCompleteId,
				"grandparent 1",
				null,
				"parent 1",
				"child 1",
				new StrIntTestEntity( "data 1", 1, siteCompleteId ),
				"baby 1"
		);
		BabyCompleteEntity baby = getAuditReader().find( BabyCompleteEntity.class, babyCompleteId, 1 );
		Assert.assertEquals( expectedBaby, baby );
		Assert.assertEquals( expectedBaby.getRelation().getId(), baby.getRelation().getId() );
	}
