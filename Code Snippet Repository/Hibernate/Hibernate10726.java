	@Test
	public void testRevisionsCounts() {
		assert Arrays.asList( 1, 2, 3, 4 ).equals( getAuditReader().getRevisions( ParentEntity.class, p1_id ) );
		assert Arrays.asList( 1, 2, 3, 4 ).equals( getAuditReader().getRevisions( ParentEntity.class, p2_id ) );

		assert Arrays.asList( 1 ).equals( getAuditReader().getRevisions( Child1Entity.class, c1_1_id ) );
		assert Arrays.asList( 1, 5 ).equals( getAuditReader().getRevisions( Child1Entity.class, c1_2_id ) );

		Assert.assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( Child2Entity.class, c2_1_id ) );
		Assert.assertEquals( Arrays.asList( 1, 5 ), getAuditReader().getRevisions( Child2Entity.class, c2_2_id ) );
	}
