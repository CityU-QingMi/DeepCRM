	@Test
	public void testRevisionCounts() {
		// companies
		assertEquals( 1, getAuditReader().getRevisions( Company.class, company1Id ).size() );
		assertEquals( 2, getAuditReader().getRevisions( Company.class, company2Id ).size() );
		assertEquals( 1, getAuditReader().getRevisions( Company.class, company3Id ).size() );
		// employees
		assertEquals( 1, getAuditReader().getRevisions( Employee.class, employee1Id ).size() );
		assertEquals( 2, getAuditReader().getRevisions( Employee.class, employee2Id ).size() );
		assertEquals( 1, getAuditReader().getRevisions( Employee.class, employee3Id ).size() );
		assertEquals( 1, getAuditReader().getRevisions( Employee.class, employee4Id ).size() );
	}
