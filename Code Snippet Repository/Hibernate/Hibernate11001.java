	@Test
	public void testCompany1EmployeeIn() {
		AuditQuery auditQuery = getAuditReader().createQuery().forRevisionsOfEntity( Employee.class, true, true );
		auditQuery.add( AuditEntity.relatedId( "company" ).in( new Integer[]{ company1Id } ) );
		final List<Employee> results = auditQuery.getResultList();
		assertEquals( 2, results.size() );
		final Employee employee1 = makeEmployee( employee1Id, "Employee1", company1Id, "COMPANY1" );
		final Employee employee2 = makeEmployee( employee2Id, "Employee2", company1Id, "COMPANY1" );
		assertThat( results.contains( employee1 ), is(true) );
		assertThat( results.contains( employee2 ), is(true) );
	}
