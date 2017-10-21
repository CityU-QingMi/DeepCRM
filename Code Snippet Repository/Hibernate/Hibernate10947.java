	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1, 2, 3 ).equals(
				getAuditReader().getRevisions(
						DoubleSetRefCollEntity.class,
						coll1_id
				)
		);

		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( StrTestEntity.class, str1_id ) );
		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( StrTestEntity.class, str2_id ) );
	}
