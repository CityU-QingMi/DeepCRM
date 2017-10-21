	@Test
	public void testCompany2EmployeeIn() {
		AuditQuery auditQuery = getAuditReader().createQuery().forRevisionsOfEntity( Employee.class, true, true );
		auditQuery.add( AuditEntity.relatedId( "company" ).in( new Integer[]{ company2Id } ) );
		final List<Employee> results = auditQuery.getResultList();
		assertEquals( 2, results.size() );

		final Employee employee1 = makeEmployee( employee2Id, "Employee2", company2Id, "COMPANY2" );
		final Employee employee2 = makeEmployee( employee3Id, "Employee3", company2Id, "COMPANY2" );
		assertThat( results.contains( employee1 ), is(true) );
		assertThat( results.contains( employee2 ), is(true) );
	}
