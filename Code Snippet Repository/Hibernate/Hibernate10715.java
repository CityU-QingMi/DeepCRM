	@Test
	public void testRevisionsCounts() {
		assertEquals( Arrays.asList( 1, 2, 4 ), getAuditReader().getRevisions( WhereJoinTableEntity.class, wjte1_id ) );
		assertEquals( Arrays.asList( 1, 3, 4 ), getAuditReader().getRevisions( WhereJoinTableEntity.class, wjte2_id ) );

		assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( IntNoAutoIdTestEntity.class, ite1_1_id ) );
		assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( IntNoAutoIdTestEntity.class, ite1_2_id ) );
		assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( IntNoAutoIdTestEntity.class, ite2_1_id ) );
		assertEquals( Arrays.asList( 1 ), getAuditReader().getRevisions( IntNoAutoIdTestEntity.class, ite2_2_id ) );
	}
