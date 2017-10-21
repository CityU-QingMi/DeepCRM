	@Test
	public void testUnversionedRelation() {
		List queryResult = getAuditReader().createQuery()
				.forRevisionsOfEntity( M2MIndexedListTargetNotAuditedEntity.class, false, true )
				.add( AuditEntity.id().eq( 1 ) )
				.add( AuditEntity.revisionType().eq( RevisionType.DEL ) )
				.getResultList();
		Object[] objArray = (Object[]) queryResult.get( 0 );

		Assert.assertEquals( 14, getRevisionNumber( objArray[1] ) );

		M2MIndexedListTargetNotAuditedEntity relationNotAuditedEntity = (M2MIndexedListTargetNotAuditedEntity) objArray[0];
		Assert.assertTrue(
				TestTools.checkCollection(
						relationNotAuditedEntity.getReferences(),
						unversionedEntity1, unversionedEntity2
				)
		);
	}
