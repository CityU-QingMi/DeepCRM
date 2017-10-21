	@Test
	@TestForIssue( jiraKey = "" )
	public void testNullReferenceWithNotFoundActionIgnore() {
		ManyToOneNotAuditedNullEntity mtoRev2 = getAuditReader().find( ManyToOneNotAuditedNullEntity.class, mtonane1.getId(), 2 );
		Assert.assertEquals( mtonane1, mtoRev2 );
		Assert.assertNull( mtoRev2.getReference() );

		ManyToManyNotAuditedNullEntity mtmRev2 = getAuditReader().find( ManyToManyNotAuditedNullEntity.class, mtmnane1.getId(), 2 );
		Assert.assertEquals( mtmnane1, mtmRev2 );
		Assert.assertTrue( mtmRev2.getReferences().isEmpty() );

		OneToManyNotAuditedNullEntity otmRev2 = getAuditReader().find( OneToManyNotAuditedNullEntity.class, otmnane1.getId(), 2 );
		Assert.assertEquals( otmnane1, otmRev2 );
		Assert.assertTrue( otmRev2.getReferences().isEmpty() );
	}
