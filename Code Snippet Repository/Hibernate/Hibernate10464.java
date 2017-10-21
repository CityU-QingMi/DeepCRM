	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1, 2 ).equals( getAuditReader().getRevisions( ComponentTestEntity.class, id1 ) );

		assert Arrays.asList( 1, 2, 4 ).equals( getAuditReader().getRevisions( ComponentTestEntity.class, id2 ) );

		assert Arrays.asList( 1, 3 ).equals( getAuditReader().getRevisions( ComponentTestEntity.class, id3 ) );

		assert Arrays.asList( 1, 2, 3 ).equals( getAuditReader().getRevisions( ComponentTestEntity.class, id4 ) );
	}
