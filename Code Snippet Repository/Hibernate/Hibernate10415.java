	@Test
	public void testRevisionsCounts() throws Exception {
		List<Number> mceId1Revs = getAuditReader().getRevisions( MultipleCollectionEntity.class, mceId1 );
		List<Number> re1Id1Revs = getAuditReader().getRevisions( MultipleCollectionRefEntity1.class, re1Id1 );
		List<Number> re1Id2Revs = getAuditReader().getRevisions( MultipleCollectionRefEntity1.class, re1Id2 );
		List<Number> re1Id3Revs = getAuditReader().getRevisions( MultipleCollectionRefEntity1.class, re1Id3 );
		List<Number> re2Id1Revs = getAuditReader().getRevisions( MultipleCollectionRefEntity2.class, re2Id1 );
		List<Number> re2Id2Revs = getAuditReader().getRevisions( MultipleCollectionRefEntity2.class, re2Id2 );
		List<Number> re2Id3Revs = getAuditReader().getRevisions( MultipleCollectionRefEntity2.class, re2Id3 );

		assertEquals( Arrays.asList( 1, 2, 3 ), mceId1Revs );
		assertEquals( Arrays.asList( 2, 3 ), re1Id1Revs );
		assertEquals( Arrays.asList( 2, 3 ), re1Id2Revs );
		assertEquals( Arrays.asList( 3 ), re1Id3Revs );
		assertEquals( Arrays.asList( 2, 3 ), re2Id1Revs );
		assertEquals( Arrays.asList( 2, 3 ), re2Id2Revs );
		assertEquals( Arrays.asList( 3 ), re2Id3Revs );
	}
