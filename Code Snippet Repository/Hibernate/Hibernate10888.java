	@Test
	public void testRevisionCounts() {
		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( NotInsertableEntityType.class, type_id1 ) );
		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( NotInsertableEntityType.class, type_id2 ) );

		assert Arrays.asList( 2, 3 ).equals(
				getAuditReader().getRevisions(
						ManyToOneNotInsertableEntity.class,
						mto_id1
				)
		);
	}
