	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1, 2, 4 ).equals( getAuditReader().getRevisions( ListOwnedEntity.class, ed1_id ) );
		assert Arrays.asList( 1, 2, 3, 5 ).equals( getAuditReader().getRevisions( ListOwnedEntity.class, ed2_id ) );

		assert Arrays.asList( 1, 2, 3, 4, 5 ).equals(
				getAuditReader().getRevisions(
						ListOwningEntity.class,
						ing1_id
				)
		);
		assert Arrays.asList( 1, 2 ).equals( getAuditReader().getRevisions( ListOwningEntity.class, ing2_id ) );
	}
