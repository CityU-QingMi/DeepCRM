	@Test
	@FailureExpected( jiraKey = "")
	public void testHistoryOfId1() {
		MappedSuperclassComponentSetTestEntity entity = getAuditReader().find(
				MappedSuperclassComponentSetTestEntity.class,
				id1,
				1
		);
		assertEquals( 0, entity.getComps().size() );
		assertEquals( 0, entity.getCompsNotAudited().size() );

		entity = getAuditReader().find( MappedSuperclassComponentSetTestEntity.class, id1, 2 );

		// TODO: what is the expectation here? The collection is audited, but the embeddable class
		// has no data and it extends a mapped-superclass that is not audited.
		// currently the collection has 1 element that has value AbstractCode.UNDEFINED
		// (which seems wrong). I changed the expected size to 0 which currently fails; is that what
		// should be expected?
		Set<Code> comps1 = entity.getComps();
		assertEquals( 0, comps1.size() );

		// The contents of entity.getCompsNotAudited() is unspecified, so no need to test.
	}
