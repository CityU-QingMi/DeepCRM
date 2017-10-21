	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1, 3, 4 ).equals( getAuditReader().getRevisions( EmbIdTestEntity.class, id1 ) );

		assert Arrays.asList( 2, 3, 4, 5 ).equals( getAuditReader().getRevisions( EmbIdTestEntity.class, id2 ) );

		assert Arrays.asList( 1, 3, 4 ).equals( getAuditReader().getRevisions( MulIdTestEntity.class, id3 ) );

		assert Arrays.asList( 2, 3 ).equals( getAuditReader().getRevisions( MulIdTestEntity.class, id4 ) );

		assert Arrays.asList( 1, 3, 4 ).equals(
				getAuditReader().getRevisions(
						EmbIdWithCustomTypeTestEntity.class,
						id5
				)
		);

		assert Arrays.asList( 2, 3, 4 ).equals(
				getAuditReader().getRevisions(
						EmbIdWithCustomTypeTestEntity.class,
						id6
				)
		);
	}
